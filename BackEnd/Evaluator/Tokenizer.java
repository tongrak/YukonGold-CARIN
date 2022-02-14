import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;

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
     * @return latest token or Null if Stream was empty
     * */
    String peer();
    /** return and remove latest token
     * @return latest token or Null if Stream was empty
     * @sideEffect remove latest token
     * */
    String pop();
}

public class Tokenizer  implements TokenizerInter{

    private LinkedList<String> tokenStream;

    @Override
    public void tokenize(Path path) {
        LinkedList<String> list = new LinkedList<>();
        Charset charset = StandardCharsets.US_ASCII;

        try(BufferedReader reader = 
            Files.newBufferedReader(path, charset)){
                String line;
                while((line = reader.readLine()) != null){
                    String[] Splittedline = line.split(" ");
                    list.addAll(Arrays.asList(Splittedline));
                }
                this.tokenStream = list;
        }catch(IOException e){
            System.err.format("IOException: %s%n",e);
        }
    }

    @Override
    public String peer() {
        return tokenStream.peek();
    }

    @Override
    public String pop() {
        return tokenStream.poll();
    }
}