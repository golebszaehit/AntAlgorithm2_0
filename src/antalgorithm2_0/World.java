
package antalgorithm2_0;


public class World {
public int lp;         // liczba punkt�w �wiata

        public Punkt punkty[]; // tablica punkt�w �wiata
        public Auxil aux[];      // tablica pomocnicza do oblicze�
        public char home;      // nazwa punktu mrowiska
        public char food;      // nazwa punktu zawieraj�cego pokarm
        public int lpw;        // liczba punkt�w wyboru dla mr�wek
        public int hmfood;     // ilość pokarmu
    public float feromon;
        int tabela[][]={{'a',6,1},{'b',13,1},{'c',4,3},{'d',4,5},{'e',8,5},
                        {'f',6,8,},{'g',10,8}}; //konfiguracja �wiata     
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
        public void ferom_reset(){
            // zeruje ilo�� foremonu we wszystkich punktach �wiata// pomniejsza ilość feromonu we wszystkich punktach świata.
            for(int i=0;i<lp;i++) {
                System.out.println(punkty[i].name + " " + punkty[i].ferom);
                if (i == punkty[i].ferom || i < punkty[i].ferom) {
                    float procentferomonu = (float) punkty[i].ferom/2;
                    //procentferomonu=Math.round(procentferomonu);
                    double roundOff = Math.round(procentferomonu*100.0)/100.0; // odcięcie tezaokrąglenie do dwóch po przecinku
                    //System.out.println(roundOff);
                    punkty[i].ferom = punkty[i].ferom - roundOff; // odjęcie reszty poza tym co jest do dwóch po przecinku.
                }
            }
        }
        public void pokaz () {
            System.out.println();
            for (int i = 0; i < lp; i++)
                System.out.print((char) punkty[i].name + "   ");
            System.out.println();
            for (int i = 0; i < lp; i++)
                System.out.print((float) punkty[i].ferom + "   ");
            System.out.println();
        }

    }


