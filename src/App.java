public class App {
    public static void main(String[] args) throws Exception {
        String codejava=".";
        String urlimage = codejava+"/images/";
        //String urlfile = codejava+"/file/";
        compressor compress = new compressor(urlimage+"test.jpg");
        compress.Compress(urlimage+"Compress.jpg", urlimage+"Control.jpg",2);
        //compress.CompressAndBinary(urlfile+"test.txt");
    }
}
