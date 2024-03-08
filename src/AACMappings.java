import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

public class AACMappings {
  private AssociativeArray<String, AACCategory> categories;
  private PrintWriter err;
  private String currentCategory;

  public AACMappings(String filename) {
    this.err = new PrintWriter(System.err, true);
    this.categories = new AssociativeArray<String, AACCategory>();
    this.currentCategory = "";

    // Open file
    File f = new File(filename);

    try {
      Scanner fileScanner = new Scanner(f);

      // Parse file line-by-line
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        if (line.startsWith(">")) {
          parseImageMapping(line);
        } else {
          currentCategory = parseAacCategory(line);
        }
      }
      
      // Close scanner
      fileScanner.close();
    } catch (FileNotFoundException e) {
      err.println(e.getMessage());
    }
    reset();
  }

  /**
   * Given the image location selected, it determines the associated text with the image.
   * If the image provided is a category, it also updates the AAC's current category
   * to be the category associated with that image
   */
  public String getText(String imageLoc) {
    if (isCategory(imageLoc)) {
      currentCategory = imageLoc;
      try {
        return categories.get(currentCategory).name;
      } catch (KeyNotFoundException e) {
        err.println("getText category:" + e.getMessage());
        return "";
      }
    } else {
      try {
        return categories.get(currentCategory).getText(imageLoc);
      } catch (KeyNotFoundException e) {
        err.println("getText image:" + e.getMessage());
        return "";
      }
    }
  }

  /**
   * Provides an array of all the images in the current category
   */
  public String[] getImageLocs() {
    if (currentCategory.equals("")) {
      return this.categories.keys();
    } else {
      try {
        return this.categories.get(currentCategory).getImages();
      } catch (KeyNotFoundException e) {
        err.println("getImageLocs:" + e.getMessage());
        return new String[] {};
      }
    }
  }

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    currentCategory = "";
  }

  /**
   * Gets the current category
   */
  public String getCurrentCategory() {
    return currentCategory;
  }

  /**
   * Determines if the image represents a category or text to speak
   */
  public boolean isCategory(String imageLoc) {
    return categories.hasKey(imageLoc);
  }

  /**
   * Writes the ACC mappings stored to a file. The file is formatted as the text
   * location of the category followed by the text name of the category and then
   * one line per item in the category that starts with > and then has the file
   * name and text of that image.
   */
  public void writeToFile(String filename) {
    File f = new File(filename);

    try {
      PrintWriter fpw = new PrintWriter(f);
      String[] categoryImages = categories.keys();

      for (String img : categoryImages) {
        writeAacCategory(img, categories.get(img), fpw);
      }

      fpw.close();
    } catch (FileNotFoundException e) {
      err.println("write to file: cannot write to file:" + e.getMessage());
    } catch (KeyNotFoundException e) {
      err.println("write to file: key not found:" + e.getMessage());
    }
  }

  /**
   * Adds the mapping to the current category (or the default category if that
   * is the current category)
   */
  public void add(String imageLoc, String text) {
    if (getCurrentCategory().equals("")) {
      try {
        this.categories.set(imageLoc, new AACCategory(text));
      } catch (NullKeyException e) {
        err.println("add category:" + e.getMessage());
      }
    } else {
      try {
        categories.get(getCurrentCategory()).addItem(imageLoc, text);
      } catch (KeyNotFoundException e) {
        err.println("add:" + e.getMessage());
      }
    }
  }

  // +-----------------+
  // | Private methods |
  // +-----------------+

  /**
   * Reads a line representing a new AACCategory.
   * Adds the image path and new AACCategory to the categories associative array.
   * Returns the image path of this AACCategory, or empty string for null input.
   * 
   * @pre `categories` must be initialized
   * @pre `line` must follow the format "[path] [category name]"
   *  (category name may contain spaces)
   */
  private String parseAacCategory(String line) {
    String[] pair = line.split(" ", 2);

    String path = pair[0];
    String categoryName = pair[1];

    try {
      categories.set(path, new AACCategory(categoryName));
      return path;
    } catch (NullKeyException e) {
      err.println(e.getMessage());
      return "";
    }
  }

  /** 
   * Reads a line representing a mapping from an image to a spoken text.
   * Adds this mapping to the current AACCategory.
   * 
   * @pre `line` must follow the format ">[path] [image text]"
   *  (image text may contain spaces)
   */
  private void parseImageMapping(String line) {
    String[] pair = line.substring(1).split(" ", 2);

    String imagePath = pair[0];
    String imageText = pair[1];

    add(imagePath, imageText);
  }

  /**
   * Writes the path of the image and name of an AACCategory, followed by the
   * contents of this AACCategory using a PrintWriter in a format readable by
   * the constructor of this class.
   */
  private void writeAacCategory(String imagePath, AACCategory cat, PrintWriter pen) {
    pen.println(imagePath + " " + cat.name);

    String[] paths = cat.getImages();
    for (String path : paths) {
      pen.println(">" + path + " " + cat.getText(path));
    }
  }
}
