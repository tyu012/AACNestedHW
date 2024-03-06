import structures.AssociativeArray;

/**
 * A mapping between image location strings and image description strings,
 * implemented using an associative array.
 * 
 * @author Tim Yu
 */
public class AACCategory {
  String name;
  private AssociativeArray<String, String> imageMappings;

  /**
   * Creates a new empty category with the given name
   */
  public AACCategory(String name) {
    this.name = name;
    this.imageMappings = new AssociativeArray<String, String>();
  }

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   */
  public void addItem(String imageLoc, String text) {
    return;
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
