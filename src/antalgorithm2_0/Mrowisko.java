
package antalgorithm2_0;


public class Mrowisko {

    public World a;
    private int lmr;              // liczba mrówek w mrowisku
    private Mrowka ants[];        // tablica mrówek
    public Mrowisko(World swiat, int _lmr, float _fer){
        // mrowisko tworzy społeczność mrówek w określonym świecie
       lmr=_lmr; ants=new Mrowka[lmr];
       for(int i=0;i<lmr;i++){
         ants[i]=new Mrowka(swiat);
         ants[i].polozenie=swiat.home;
         ants[i].feromon=_fer;
         ants[i].syta=false;
         ants[i].sciezka+=swiat.home;
         }
    }
    public void reset(){
        // ustawia wszystkie mrówki w położeniu home,
        // zeruje ich ścieżki i ilość foremonu w punktach
        for(int i=0;i<lmr;i++){
         ants[i].polozenie=ants[i].swiat.home;
         ants[i].sciezka+=ants[i].swiat.home;}
    }
    public void move(){    
        // w każdym cyklu przesuwa wszystkie mrówki do kolejnych punktów, 
        // najlepszych z trzech losowo (lub za pomocą ruletki) wybranych,
        // po przesunięciu wykonuje na każdej mrówce metodę "akcja" 
        // z klasy Mrówka
        for(int i=0;i<lmr;i++)
        {
            if (!ants[i].syta) {
                ants[i].akcja();
            }
        }
    }
   public void pokaz(){
        for(int i=0;i<lmr;i++)
          ants[i].pokaz();
   }
}
