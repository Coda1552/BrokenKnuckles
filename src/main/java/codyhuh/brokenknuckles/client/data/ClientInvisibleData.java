package codyhuh.brokenknuckles.client.data;

public class ClientInvisibleData {
    private static boolean invisible;

    public static void toggleInvisible(){
        ClientInvisibleData.invisible = !ClientInvisibleData.invisible;
    }

    public static boolean getInvisible(){
        return invisible;
    }
}
