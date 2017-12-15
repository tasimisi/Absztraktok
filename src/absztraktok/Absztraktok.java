package absztraktok;

import static java.lang.System.out;
import java.util.Scanner;

abstract class Sikidom {
    protected boolean tukorszimmetrikus;
    
    public Sikidom(boolean tukorszimmetrikus) {
        this.tukorszimmetrikus = tukorszimmetrikus;
    }
    
    public abstract double kerulet();
    public abstract double terulet();    
      
    public void kiir() {
        out.println("Kerület: "+kerulet()+" m");
        out.println("Terület: "+terulet()+" m2");        
        out.println((tukorszimmetrikus ? "T":"Nem t") + "ükörszimmetrikus");
    }    
}

class Kor extends Sikidom {
    private static final double PI = 3.1415;
    private double r;
    
    public Kor(double sugar) {
        super( true );
        r = sugar;
    }
    
    @Override
    public double kerulet() {
        return 2 * r * PI;
    }
    
    @Override
    public double terulet() {
        return Math.pow(r,2) * PI;
    }       
}


class Teglalap extends Sikidom {
    double a,b;
    
    public Teglalap(double aOldal, double bOldal) {
        super( (aOldal==bOldal ? true:false) );
        a = aOldal;
        b = bOldal;
    }
    
    @Override
    public double kerulet() {
        return 2 * (a+b);
    }
    
    @Override
    public double terulet() {
        return a * b;
    }           
}

abstract class Targy {
    protected boolean tukorszimmetrikus;
    
    public Targy(boolean tukorszimmetrikus) {
        this.tukorszimmetrikus = tukorszimmetrikus;
    }
    
    public abstract double felulet();
    public abstract double terfogat();    
    
    public abstract double falfelulet();
    public abstract double falterfogat(); 
      
    public void kiir() {
        out.println("Felszíne: "+felulet()+" m2");
        out.println("Térfogat: "+terfogat()+" m3");        
        out.println((tukorszimmetrikus ? "T":"Nem t") + "ükörszimmetrikus");
        out.println("Fal belső felszíne: "+falfelulet()+" m2");
        out.println("Fal térfogata: "+falterfogat()+" m3");   
    }    
}

class Gomb extends Targy {
    private static final double PI = 3.1415;
    private double r;
    private double w; //wall width
    
    public Gomb(double sugar, double ballwall) {
        super( true );
        r = sugar;
        w = ballwall;
    }
    
    @Override
    public double felulet() {
        return 4 * Math.pow(r,2) * PI;
    }
    
    @Override
    public double terfogat() {
        return (4 * Math.pow(r,3) * PI)/3;
    }           
    
    @Override
    public double falfelulet() {
        return (4 * Math.pow((r-w),2)* PI);
    }
    
    @Override
    public double falterfogat() {
        return (((4 * Math.pow(r,3) * PI)/3)-((4 * Math.pow((r-w),3) * PI)/3));
    }
}

class Teglatest extends Targy {
    double a,b,c;
    private double cw;   //cuboid wall width
    
    public Teglatest(double aOldal, double bOldal, double cOldal, double cuboidwall) {
        super( (aOldal==bOldal && aOldal==cOldal ? true:false) );
        a = aOldal;
        b = bOldal;
        c = cOldal;
        cw= cuboidwall;
    }
    
    @Override
    public double felulet() {
        return 2 * (a*b+a*c+b*c);
    }
    
    @Override
    public double terfogat() {
        return a * b * c;
    }               
    
    @Override
    public double falfelulet() {
        return 2 * ((a-(2*cw))*(b-(2*cw))+(a-(2*cw))*(c-(2*cw))+(b-(2*cw))*(c-(2*cw)));
    }
    
    @Override
    public double falterfogat() {
        return (a * b * c)-((a-(2*cw)) * (b-(2*cw))* (c-(2*cw)));
    }               
}

public class Absztraktok {
  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double sugar;
        double a,b,c;
        double ballwall;
        double cuboidwall;
        
        out.println("Kérem a kör sugarát méterben:");
        sugar = input.nextDouble();
        Sikidom kor = new Kor(sugar);
        kor.kiir();
        
        out.println("Kérem a téglap a oldal hosszát méterben:");
        a = input.nextDouble();
        out.println("Kérem a téglap b oldal hosszát méterben:");
        b = input.nextDouble();
        Sikidom teglalap = new Teglalap(a,b);
        teglalap.kiir();
        
        out.println("Kérem a gömb sugarát méterben:");
        sugar = input.nextDouble();
        out.println("Kérem a gömb falának vastagságát:");
        ballwall= input.nextDouble();
        Targy gomb = new Gomb(sugar,ballwall);
        gomb.kiir();
        
        out.println("Kérem a téglatest a oldalának hosszát méterben:");
        a = input.nextDouble();
        out.println("Kérem a téglatest b oldalának hosszát méterben:");
        b = input.nextDouble();
        out.println("Kérem a téglatest c oldalának hosszát méterben:");
        c = input.nextDouble();        
        out.println("Kérem a téglatest falának vastagságát méterben:");
        cuboidwall = input.nextDouble();   
        Targy teglatest = new Teglatest(a,b,c,cuboidwall);
        teglatest.kiir();
        
        
        
    }
    
}
