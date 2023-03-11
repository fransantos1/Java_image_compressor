public class App {
    public static void main(String[] args) throws Exception {
        String url = "../images/";
        compressor compress = new compressor(url+"test.jpg");
        compress.Compress(url+"Compress.jpg", url+"Control.jpg",2);
    }
}
