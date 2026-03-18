import java.util.Scanner;
public class Sifre {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Şifre Güvenlik Analiz Aracına Hoş Geldin ---");
        System.out.print("Lütfen test etmek istediğiniz şifreyi girin: ");
        String sifre = scanner.nextLine();

        // Şifreyi analiz edip puanlayan metodu çağırıyoruz
        int skor = sifrePuanla(sifre);
        
        // Gelen skora göre kullanıcıya dönüt veriyoruz
        sonucuDegerlendir(skor);

        scanner.close();
    }

    // Şifrenin özelliklerine göre bir puan hesaplayan metod
    public static int sifrePuanla(String sifre) {
        int skor = 0;

        // 1. Uzunluk Kontrolleri
        if (sifre.length() >= 8) skor += 1;
        if (sifre.length() >= 12) skor += 1;

        // 2. Karakter Çeşitliliği Kontrolleri
        boolean kucukHarfVar = false;
        boolean buyukHarfVar = false;
        boolean rakamVar = false;
        boolean ozelKarakterVar = false;

        // Şifredeki her bir karakteri tek tek dönüp ne olduğuna bakıyoruz
        for (char c : sifre.toCharArray()) {
            if (Character.isLowerCase(c)) kucukHarfVar = true;
            else if (Character.isUpperCase(c)) buyukHarfVar = true;
            else if (Character.isDigit(c)) rakamVar = true;
            else ozelKarakterVar = true; // Boşluk ve diğer tüm semboller
        }

        // Çeşitliliğe göre skoru artırıyoruz
        if (kucukHarfVar) skor += 1;
        if (buyukHarfVar) skor += 1;
        if (rakamVar) skor += 1;
        if (ozelKarakterVar) skor += 1;

        // Toplam alınabilecek maksimum skor 6. Bunu 5'li seviye sistemine uyarlıyoruz:
        if (skor <= 2) return 1; // 1: Çok Zayıf
        if (skor == 3) return 2; // 2: Zayıf
        if (skor == 4) return 3; // 3: Normal
        if (skor == 5) return 4; // 4: Güçlü
        if (skor >= 6) return 5; // 5: Çok Güçlü

        return 1;
    }

    // Skoru metne döküp ekrana yazdıran metod
    public static void sonucuDegerlendir(int seviye) {
        System.out.println("\n------------------------------------------------");
        switch (seviye) {
            case 1:
                System.out.println("Güvenlik Düzeyi: 🔴 ÇOK ZAYIF");
                System.out.println("Açıklama: Şifreniz çok kısa veya tek tip karakter içeriyor. Saniyeler içinde kırılabilir!");
                break;
            case 2:
                System.out.println("Güvenlik Düzeyi: 🟠 ZAYIF");
                System.out.println("Açıklama: Şifreniz biraz daha uzun olabilir. Karışık karakterler eklemeyi deneyin.");
                break;
            case 3:
                System.out.println("Güvenlik Düzeyi: 🟡 NORMAL");
                System.out.println("Açıklama: İdare eder bir şifre. Ancak büyük harf veya özel semboller (!, ?, %, &) ekleyerek güçlendirmelisin.");
                break;
            case 4:
                System.out.println("Güvenlik Düzeyi: 🟢 GÜÇLÜ");
                System.out.println("Açıklama: Gayet iyi bir şifre. Günlük hesapların için oldukça güvenli.");
                break;
            case 5:
                System.out.println("Güvenlik Düzeyi: 🔵 ÇOK GÜÇLÜ");
                System.out.println("Açıklama: Mükemmel! Siber korsanların korkulu rüyasısın. Bu şifreyi kırmak yıllar sürer.");
                break;
            default:
                System.out.println("Bilinmeyen bir hata oluştu.");
        }
        System.out.println("------------------------------------------------\n");
    }
}
