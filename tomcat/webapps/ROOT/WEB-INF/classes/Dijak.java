public class Dijak{
    public String ime;
    public String priimek;
    public String datum;
    public String razred;

    public Dijak (){
        this.ime = "";
        this.priimek = "";
        this.datum = "";
        this.razred = "";
    }

    public Dijak (String i, String p, String d, String r){
        this.ime = i;
        this.priimek = p;
        this.datum = d;
        this.razred = r;
    }

    public void setIme(String i){
        this.ime = i;
    }

    public void setPriimek(String p){
        this.priimek = p;
    }

    public void setDatum(String d){
        this.datum = d;
    }

    public void setRazred(String r){
        this.razred = r;
    }

    public void izpis(int v){
        System.out.println(v+". " + this.ime + " " + this.priimek + " " + this.datum + " " + this.razred);
    }


}