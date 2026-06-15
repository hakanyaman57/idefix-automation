# Idefix Automation

Bu proje Idefix uzerinde urun arama, sepete ekleme, fiyat karsilastirma ve sepet temizleme adimlarini Gauge BDD ile otomatik test etmek icin hazirlanmistir.

## Kullanilan Teknolojiler

- Java 21
- Maven
- Gauge
- Selenium 4
- Google Chrome
- Jackson Databind
- Log4j2
- AssertJ

## Mimari

Projede Page Object Model kullanilir. Page Factory tercih edilmemistir; Idefix React/Next.js tabanli dinamik bir sayfa oldugu icin elementler her aksiyonda `By` locator ve `WebDriverWait` ile taze sekilde bulunur. Bu yaklasim `StaleElementReferenceException` riskini azaltir.

Tarayici tarafinda sistemde kurulu olan Google Chrome kullanilir. `chromedriver` binary projeye eklenmez; Selenium 4 Selenium Manager sistemdeki Chrome versiyonuna uygun driver'i otomatik olarak cozer ve ilk calistirmada cache'e indirir. Maven tarafinda sadece Chrome icin gerekli Selenium modulleri kullanilir; farkli tarayici driver bagimliliklari projeye eklenmez.

Sayfa yukleme stratejisi `eager` olarak ayarlanmistir. Idefix gibi cok sayida ucuncu parti kaynak yukleyen e-ticaret sayfalarinda testin tum asset/network beklemelerine takilmamasi icin Selenium, DOM hazir oldugunda devam eder ve element bazli explicit wait kullanilir.

## Proje Yapisi

- `specs`: Gauge spec dosyalari
- `src/test/java/com/idefix/base`: Driver ve temel page islemleri
- `src/test/java/com/idefix/pages`: Page Object classlari
- `src/test/java/com/idefix/steps`: Gauge step implementasyonlari
- `src/test/java/com/idefix/hooks`: Gauge hook classi
- `src/test/java/com/idefix/models`: JSON test datasina karsilik gelen modeller
- `src/test/java/com/idefix/utils`: Config, JSON, fiyat ve dosya yardimcilari
- `src/test/resources`: Config, test data ve log ayarlari

## Calistirma

Bagimliliklari derlemek icin:

```powershell
mvn test-compile
```

Gauge runner'in Maven bagimliliklarini gorebilmesi icin:

```powershell
mvn dependency:copy-dependencies -DincludeScope=test
```

Yavas makinelerde veya ilk dependency taramasinda Gauge Java runner gec acilirsa:

```powershell
gauge config runner_connection_timeout 180000
```

Gauge senaryosunu calistirmak icin:

```powershell
gauge run specs
```

Raporlar `reports` klasorunde, loglar `logs` klasorunde olusur. Secilen urun bilgisi `output/product-info.txt` dosyasina yazilir.

## Test Verisi

Test verileri `src/test/resources/test-data.json` dosyasindan okunur. Kullanici bilgileri, arama kelimeleri ve secilecek urun sirasi bu dosyada tutulur. Gercek hesap bilgileri kullaniliyorsa bu dosya paylasimdan once kontrol edilmelidir.

Ortam URL'i `src/test/resources/config.properties` dosyasindaki `base.url` degeri ile parametrik olarak yonetilir. Stage veya farkli test ortami icin bu deger guncellenir.
