import java.util.Scanner;

public class MTU{

    /**
     * @realArcherL
     * MTU (Maximum Transmission Unit) 32-(router memory) Bytes
     * IP Header size 20-60 Bytes
     * Shows output of Total Size of the packet, Size of Data, More Fragments & Fragment Offset.
     * */

     static final int mtu =1480;
    static final int headerSize = 20;

    static int[] packets;
    static int[] totalSize;
    static int[] offset;
    static boolean[] fragments;

    public int numberOfPackets(int data){
        return (int)Math.ceil((float)data/mtu);
    }

    public static void printPackets(int[] array){
        for (int i=0; i<array.length; i++){
            System.out.print(array[i]+ "|" + headerSize + " ");
        }
    }

    public static void printMoreFragments(boolean[] array){
        for (int i=0; i<array.length; i++){
            System.out.print(array[i]+ " ");
        }
    }

    public void packetDivision(int data){

        packets = new int[numberOfPackets(data)];
        totalSize = new int[numberOfPackets(data)];
        offset = new int[numberOfPackets(data)];

        for(int i=0; i<packets.length; i++) {
            if (data <= mtu)
                packets[i] = data;
            else
                packets[i] = mtu;
            data = data - mtu;
        }

        for(int i=0; i<totalSize.length; i++){
            totalSize[i] = packets[i]+ headerSize;
        }

        offset[0] = 0;
        for (int i=1; i<offset.length; i++){
            offset[i] = offset[i-1] +packets[i-1];
        }

    }

    public void moreFragments(int data){

        fragments = new boolean[numberOfPackets(data)];

        for(int i=0; i<packets.length; i++){
            fragments[i] = true;

            if (packets[i] < mtu){
                fragments[i] = false;
            }
        }
    }

    public static void main(String[] args){

        MTU mtu = new MTU();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of Bytes to be sent:  ");
        int data = sc.nextInt();

        mtu.packetDivision(data);
        System.out.print("Total Size : ");
        printPackets(totalSize);

        System.out.println();

        System.out.print("Packets    : ");
        printPackets(packets);

        System.out.println();

        System.out.print("Frag Offset: ");
        printPackets(offset);

        System.out.println();

        mtu.moreFragments(data);
        System.out.print("MoreFrag   : ");
        printMoreFragments(fragments);
    }
}
