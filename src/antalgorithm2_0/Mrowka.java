
package antalgorithm2_0;

public class Mrowka {
    final double MAX=1e6; // nie spotykana odległość między punktami
    public World swiat; 
        // wskazanie świata, do którego należy mrówka
    public char polozenie;
    public float feromon; 
        // ilość pozostawianego feromonu w punkcie
    boolean syta;
    String sciezka="";
    public Mrowka(World swiat){
        this.swiat=swiat;}
    private void policz_odleglosci(){
        // liczy odleglości od aktualnego położenia do pozostałych
        // punktów (oprócz własnego i mrowiska)
        int index=polozenie-'a';
        int dx,dy;
        for(int i=0;i<swiat.lp;i++)
          if((i==index)||(i==(swiat.home-'a')))
            swiat.aux[i].ratio=MAX;
          else{
            dx=swiat.punkty[index].x-swiat.punkty[i].x;
            dy=swiat.punkty[index].y-swiat.punkty[i].y;
            swiat.aux[i].ratio=Math.sqrt(dx*dx+dy*dy);
          }
    }
    private void sortuj_tablice_pomocnicza(){
        // zapisuje w tablicy "aux" wartości
        // ilorazów "feromon/odległość" dla punktów
        // a następnie sortuje je rosnąco
        for(int i=0;i<swiat.lp;i++)
           swiat.aux[i].ratio=(swiat.punkty[i].ferom+1)/swiat.aux[i].ratio;
        Auxil bufor=new Auxil();
        for(int i=swiat.lp-1;i>0;i--)
           for(int j=1;j<=i;j++)
             if(swiat.aux[j].ratio>=swiat.aux[j-1].ratio){
                bufor.name=swiat.aux[j].name;
                bufor.ratio=swiat.aux[j].ratio;
                swiat.aux[j].name=swiat.aux[j-1].name;
                swiat.aux[j].ratio=swiat.aux[j-1].ratio;
                swiat.aux[j-1].name=bufor.name;
                swiat.aux[j-1].ratio=bufor.ratio;
        }
    }
    private boolean sprawdz_sciezke(char znak){
        // sprawdza, czy w sciezce występuje podany znak,
        boolean jest=false; int i=0;
        while((!jest)&&(i<sciezka.length()))
            if(sciezka.charAt(i)==znak)jest=true; else i++;
        return jest;
    }
    private int losuj(){
         // losuje kolejne położenie mrówki
         return(int) ((Math.random() * 10) / swiat.lpw);
    }
    private int ruletka(){
        // wybiera metodą ruletki kolejne położenie mrówki
        double help[]; help=new double[swiat.lpw];
        double suma=0; double losowa=0;
        for(int i=0;i<swiat.lpw;i++)
          suma+=swiat.aux[i].ratio;
        for(int i=0;i<swiat.lpw;i++)
        {help[i]=swiat.aux[i].ratio/suma;
        losowa=Math.random();} 
        int i=0; suma=help[i];
        while(losowa>suma)suma+=help[++i];
        return i;
    }
    
    public int wybierz_punkt(){ 
        // zwraca index kolejnego punktu
        for(int i=0;i<swiat.lp;i++)// resetuje tablicę pomocniczą
          swiat.aux[i].name=swiat.punkty[i].name;
        policz_odleglosci();
        sortuj_tablice_pomocnicza();
        int wybor; boolean zly_wybor;
      do{ 
         wybor=ruletka();// wybór kolejnego punktu przy pomocy ruletki/losuj
         zly_wybor=sprawdz_sciezke(swiat.aux[wybor].name);
      }while(zly_wybor);
    return wybor;
    }
    public void akcja(){
        // sprawdza, czy mrówka osiągnęła pokarm,
        // jeśli NIE - dopisuje osiągnięt y punkt do jej ścieżki
        // jeśli TAK - dodaje do każdego punktu  na ścieżce feromon,
        //             i "usypia" mrówkę za pomocą "syta=true"
        int index=wybierz_punkt();
     int valor=(swiat.aux[index].name!=(swiat.food))?1:0;
     switch (valor)
     { case 1: polozenie=swiat.aux[index].name;
               sciezka+=polozenie;
                //System.out.println("case 1: " + valor);
               break;
       case 0: syta=true;
               polozenie=swiat.food;
               sciezka+=polozenie;

                sciezka="";
                polej_sciezke();
                polozenie=swiat.home;
                sciezka+=polozenie;

                //System.out.println("case 0: " + valor);
                //System.out.println(sciezka+=polozenie);
               break;
       }
       /*case 0:
           if (swiat.hmfood>0) {syta=true;}
               //polozenie=swiat.food;



               if (swiat.hmfood>=1)
                   swiat.hmfood = swiat.hmfood-1;
               sciezka="";
               polej_sciezke();
               polozenie=swiat.home;
               sciezka+=polozenie;


               sciezka="";
               polozenie=swiat.home;


           System.out.println(sciezka+=polozenie);
           System.out.println(swiat.hmfood);

           break;

     }*/
    }
    void polej_sciezke() {
        // akcja wykonywana na punktach świata należących
        // do ścieżki tej mrówki, która doszła do pokarmu
        for (int i = 0; i < sciezka.length(); i++) {
            if (sciezka.charAt(i) != swiat.home) {
                swiat.punkty[sciezka.charAt(i) - 'a'].ferom += feromon;
            }
        }
    }
    public void pokaz(){
        if(syta)System.out.print("syta ");else System.out.print("     ");
        System.out.printf("%7s %2d",sciezka,sciezka.length());
        System.out.println();
    }    
}
