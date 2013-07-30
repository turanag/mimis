package mimis.util;

public class Type {
    public static final int BITS_PER_BYTE = 8;
    
    public static byte[] intToBytes(int paramInt) {
        return intToBytes(paramInt, true);
    }

    public static byte[] intToBytes(int paramInt, boolean paramBoolean) {
        byte[] arrayOfByte = new byte[4];
        int i;
        if (paramBoolean) {
            for (i = 0; i < arrayOfByte.length; i++) {
                arrayOfByte[(arrayOfByte.length - i - 1)] = (byte) (paramInt >> i
                        * BITS_PER_BYTE & 0xFF);
            }
        } else {
            for (i = 0; i < arrayOfByte.length; i++) {
                arrayOfByte[i] = (byte) (paramInt >> i * BITS_PER_BYTE & 0xFF);
            }
        }
        return arrayOfByte;
    }
}
