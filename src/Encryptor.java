import java.util.*;
public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int count = 0;
        int length = str.length();
        for (int i = 0; i < numRows;i++){
            for (int k = 0;k < numCols;k++){
                if (count != length){
                    letterBlock[i][k] = str.substring(count,count+1);
                    count++;
                }
                else{
                    letterBlock[i][k] = "A";
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String temp = "";
        for (int i = 0;i<letterBlock[0].length;i++){
            for (int k = 0;k<letterBlock.length;k++){
                temp += letterBlock[k][i];
            }
        }
        return temp;
    }

    public String decryptBlock(){
        String temp = "";
        for (int i = 0;i<letterBlock.length;i++){
            for (int j =0;j<letterBlock[0].length;j++){
                temp+= letterBlock[i][j];
            }
        }
        return temp;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */

    public String encryptMessage(String message)
    {
        int amount = numCols * numRows;
        while(message.length() % amount != 0){
          message += "A";
        }
        String temp2 = "";
        int count = numCols * numRows;
        for (int i = 0;i < message.length();i+= numCols * numRows){
            String temp3 = message.substring(i,count);
            fillBlock(temp3);
        temp2 += encryptBlock();
        count += numCols * numRows;
        }
        return temp2;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage) {
//        ArrayList<String> temp = new ArrayList<>();
//        for (int i = 0; i<encryptedMessage.length();i++){
//            temp.add(encryptedMessage.substring(i,i+1));
//        }
        String temp2 = "";
        int count = numCols * numRows;
        for (int i = 0; i < encryptedMessage.length(); i += numCols * numRows) {
            String temp3 = encryptedMessage.substring(i, count);
            fillBlock_Colums(temp3);
            temp2 += decryptBlock();
            count += numCols * numRows;
        }
        ArrayList<String> temp3 = new ArrayList<String>();
        for (int i = 0;i<temp2.length();i++){
            temp3.add(temp2.substring(i,i+1));
        }
        while (temp3.get(temp3.size()-1).equals("A")){
            temp3.remove(temp3.size()-1);
        }
        temp2 = "";
        for (String i : temp3){
            temp2 += i;
        }
        return temp2;
    }
        public void fillBlock_Colums(String str){
            int count = 0;
            int length = str.length();
            for (int i = 0; i < numCols;i++){
                for (int k = 0;k < numRows;k++){
                        letterBlock[k][i] = str.substring(count,count+1);
                        count++;
                }
            }
        }

}