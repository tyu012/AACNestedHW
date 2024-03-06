public class AACCategory {
  String name;

  /**
   * Creates a new empty category with the given name
   */
  public AACCategory(String name) {
    this.name = name;
  }

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   */
  public void addItem(String imageLoc, String text) {
    return; // STUB
  }

  /**
   * Returns the name of the category
   */
  public String getCategory() {
    return ""; // STUB
  }

  /**
   * Returns the text associated with the given image loc in this category.
   */
  public String getText(String imageLoc) {
    return ""; // STUB
  }

  /**
   * Determines if the provided images is stored in the category
   */
  public boolean hasImage(String imageLoc) {
    return false; //
  }

  /**
   * Return an array of all the images in the category
   */
  public String[] getImages() {
    return new String[]{};
  }
}
