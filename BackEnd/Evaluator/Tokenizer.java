import java.nio.file.Path;

/**Tobe uses as a tokenizer for tokenize a simple text file (.txt). This is a singleton functional class which process a text file
 * in said text file in to a tokenStream[a List of string] which will store Tokens[a string]. With 2 important methods: peer and pop.
 * both peer and pop return the latest token in the stream. The different is pop will delete the Token but peer don't.
 * Example:
 *  Say text file contain following strings:
 *      virusLoc = virus
 *      if (virusLoc / 10 - 1)
 *  After receive the Path of said text file the tokenStream should look like this: ["virus", "=", "virus", "if", "(", "virusLoc", "/", "10", "-", "1"]
 *  if the client called 'peer' this class shall return "virus";
 *  then even the client called 'peer' again the class still must return "virus";
 *  until said client called 'pop' the class shall return "virus" but after that the latest token must be "=";
 * */
interface TokenizerInter{
    /** require Path of a text file to be tokenized for later uses.
     * @param path a Path of a text file
     * @sideEffect create a list of token (TokenStream) to be used later.
     * */
    void tokenize(Path path);
    /** return the latest token
     * @return latest token
     * */
    String peer();
    /** return and remove latest token
     * @return latest token
     * @sideEffect remove latest token
     * */
    String pop();
}

public class Tokenizer  implements TokenizerInter{
    @Override
    public void tokenize(Path path) {

    }

    @Override
    public String peer() {
        return null;
    }

    @Override
    public String pop() {
        return null;
    }
}