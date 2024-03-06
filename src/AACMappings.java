public class AACMappings {
  public AACMappings(String filename) {

  }

  /**
   * Given the image location selected, it determines the associated text with the image.
   * If the image provided is a category, it also updates the AAC's current category
   * to be the category associated with that image
   */
  public String getText(String imageLoc) {
    return ""; // STUB
  }

  /**
   * Provides an array of all the images in the current category
   */
  public String[] getImageLocs() {
    return new String[] { "" }; // STUB
  }

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    return; // STUB
  }

  /**
   * Gets the current category
   */
  public String getCurrentCategory() {
    return ""; // STUB
  }

  /**
   * Determines if the image represents a category or text to speak
   */
  public boolean isCategory(String imageLoc) {
    return false; // STUB
  }

  /**
   * Writes the ACC mappings stored to a file. The file is formatted as the text
   * location of the category followed by the text name of the category and then
   * one line per item in the category that starts with > and then has the file
   * name and text of that image.
   */
  public void writeToFile(String filename) {
    return; // STUB
  }

  /**
   * Adds the mapping to the current category (or the default category if that
   * is the current category)
   */
  public void add(String imageLoc, String text) {
    return; // STUB
  }
}
