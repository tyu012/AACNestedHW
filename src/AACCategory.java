import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;
import java.io.PrintWriter;

/**
 * A mapping between image location strings and image description strings,
 * implemented using an associative array.
 * 
 * @author Tim Yu
 */
public class AACCategory {
  String name;
  private AssociativeArray<String, String> imageMappings;
  private PrintWriter err;

  /**
   * Creates a new empty category with the given name
   */
  public AACCategory(String name) {
    this.name = name;
    this.imageMappings = new AssociativeArray<String, String>();
    this.err = new PrintWriter(System.err, true);
  }

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   */
  public void addItem(String imageLoc, String text) {
    try {
      this.imageMappings.set(imageLoc, text);
    } catch (NullKeyException e) {
      err.println("Image path cannot be null");
    }
  }

  /**
   * Returns the name of the category
   */
  public String getCategory() {
    return name;
  }

  /**
   * Returns the text associated with the given image loc in this category.
   */
  public String getText(String imageLoc) {
    try {
      return imageMappings.get(imageLoc);
    } catch (KeyNotFoundException e) {
      return "";
    }
  }

  /**
   * Determines if the provided images is stored in the category
   */
  public boolean hasImage(String imageLoc) {
    return imageMappings.hasKey(imageLoc);
  }

  /**
   * Return an array of all the images in the category
   */
  public String[] getImages() {
    return new String[]{}; // STUB
  }
}
