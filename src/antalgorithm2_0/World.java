
package antalgorithm2_0;


public class World {
public int lp;         // liczba punktów œwiata
        public Punkt punkty[]; // tablica punktów œwiata
        public Auxil aux[];      // tablica pomocnicza do obliczeñ
        public char home;      // nazwa punktu mrowiska
        public char food;      // nazwa punktu zawieraj¹cego pokarm
        public int lpw;        // liczba punktów wyboru dla mrówek
        int tabela[][]={{'a',6,1},{'b',13,1},{'c',4,3},{'d',4,5},{'e',8,5},
                        {'f',6,8,},{'g',10,8}}; //konfiguracja œwiata     
        public World(int _lp, char _home, char _food, int _lpw){
            lp=_lp; home=_home; food=_food; lpw=_lpw;
            punkty=new Punkt[_lp];
            aux=new Auxil[_lp];
        }
        void inicjuj(){
            for(int i=0;i<lp;i++)
             {punkty[i]=new Punkt();
               punkty[i].name =(char)tabela[i][0];
               punkty[i].x    =tabela[i][1];
               punkty[i].y    =tabela[i][2];
               punkty[i].ferom=0.0;
              aux[i]=new Auxil();
               aux[i].name='\0';
               aux[i].ratio=0.0;
             }
        }
        void ferom_reset( ){
            // zeruje iloœæ foremonu we wszystkich punktach œwiata
            for(int i=0;i<lp;i++) punkty[i].ferom=0.0;
        }
        public void pokaz( ){
            System.out.println();
            for(int i=0;i<lp;i++)
              System.out.print((char)punkty[i].name+"   ");
            System.out.println();
            for(int i=0;i<lp;i++)
              System.out.print((int)punkty[i].ferom+"   ");
            System.out.println();
        }    
}

