import java.util.Scanner;
import java.util.Random;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shaqqy
 */
public class Oljy {
    public Oljy(){
    }

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shaqqy
 */

        // TODO code application logic here
        
    
static float oljy(){
Scanner kysy=new Scanner(System.in);
Random random=new Random();
//�ljykriisej� on tapahtunut noin kymmenen vuoden v�lein,
//joten oletamme �ljykriisin todenn�k�isyydeksi vuosittain 0.1.
//�ljyn tuotannon t�ytyy pysy� sen kysynn�n mukana, joka kasvaaa jatkuvasti.
//Jos vanhat l�hteet tyrehtyv�t ja uusia ei l�ydet� tilalle, �ljyn hinta nousee, joka vaikuttaa maailmantalouteen huiomattavasti.
int kehitysapu=662000000; //Somalian saaman kehitysavun m��r� vuonna 2009, jolloin oli k�ynniss� 2008-2009
//enn�tysnopea �ljyn hinnan nousu tuotannon riitt�m�tt�m�n nousun suhteessa kysynt��n takia.
 //l�ht�kohta vuosille joilla mallia ajetaan
float �ljy=60;//�ljyn kekim��r�inen hinta vuonna 2009 usd/bbl
System.out.println("Mihin vuoteen asti ajetaan?");
int vuosi = kysy.nextInt(); //malli kysyy k�ytt�j�lt� mihin vuoteen asti peli� pelataan
for (int i=2009; i<vuosi;i++){    //vuosi on se vuosi, mihin asti mallin halutaan ajavan. Voidaan valita mielivaltaisesti.
    int �ljykriisi=random.nextInt(10);
    int nousulasku=random.nextInt(2);
    double lol=random.nextDouble();
    
    if (�ljykriisi == 0){
        nousulasku=3;
                }
    if (nousulasku==0){
        �ljy += (lol*4);}
    else if(nousulasku==1){
        �ljy += (lol*(-4));
        }
    else{
        �ljy += (lol*6);
                }

        }
    return �ljy;   
    }
}

