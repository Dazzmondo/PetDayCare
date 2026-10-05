/*
Project: Day Care console-based management system
*/

package utils;

// Responsible for save, load, and returning file name being used.
public interface ISerializer {
    void save() throws Exception;
    void load() throws Exception;
    String fileName(); // Allows user to get name of file, useful for error messages
}
