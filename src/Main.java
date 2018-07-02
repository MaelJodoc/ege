
import java.util.Scanner;

public class Main {
    private static final int DISTANCE = 3;
    private static final int BUF[] = new int[DISTANCE + 1];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //init check
        int digitsQntOrig = scanner.nextInt();
        if (digitsQntOrig < 1) {
            System.out.println(0);
            scanner.close();
            return;
        }

        //init variables
        int digitsQntCopy = digitsQntOrig;
        int pairsQnt = 0;
        int unsuitablePairsQnt = 0;
        int digitsMod29 = 0;

        //init buf
        for (int i = 0; i < BUF.length; i++) {
            BUF[i] = scanner.nextInt();
            if (i == digitsQntCopy - 1) {
                System.out.println(0);
                scanner.close();
                return;
            }
        }
        digitsQntCopy -= BUF.length;

        //brain fuck
        while (true) {
            if (BUF[0] % 29 == 0) {
                digitsMod29++;
                unsuitablePairsQnt += BUF.length - 1;
            } else {
                for (int i = 1; i < BUF.length; i++) {
                    if (BUF[i] % 29 == 0) unsuitablePairsQnt++; //(BUF[0]*BUF[i])%29==0 little optimization
                }
            }
            digitsQntCopy--;
            if (digitsQntCopy == -1) break;

            for (int i = 0; i < BUF.length - 1; i++) {
                BUF[i] = BUF[i + 1];
            }
            BUF[BUF.length - 1] = scanner.nextInt();
        }
        scanner.close();

        //check a last numbers of buf
        for (int i = 1; i < BUF.length; i++) {
            if (BUF[i] % 29 == 0) digitsMod29++;
            for (int j = i + 1; j < BUF.length; j++) {
                if ((BUF[i] * BUF[j]) % 29 == 0) unsuitablePairsQnt++;
            }
        }

        //count result
        for (int i = 0; i < digitsMod29; i++) {
            pairsQnt += digitsQntOrig - (i + 1);
        }
        System.out.println(pairsQnt - unsuitablePairsQnt);
    }
}

