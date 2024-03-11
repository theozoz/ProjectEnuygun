Homepage Heading
=====================
Created by ozcan.arpaci on 3/9/2024

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

* One trust kabul edilir

Case 1 - Enuygun sitesinden uçuş arama
------------------------------------------------
tags: dailyTag
* Ana sayfa görüntülenir
* Bilet tipi olarak uçak seçili olduğu kontrol edilir
* Kalkış noktası için "İstanbul" havaalanı seçilir
* Varış noktası için "Ankara" havaalanı seçilir
* Seyahat tipi için gidiş-dönüş seçilir
* Gidiş tarihi olarak "2024-12-15" seçilir
* "1" saniye beklenir
* Gönüş tarihi olarak "2024-12-18" seçilir
* "1" saniye beklenir
* Seyahat araması yapılır
* Arama detay sayfasında "İstanbul" kalkış "Ankara" varış yeri ve gidiş "departureDateStoreKey" donuş "returnDateStoreKey" tarihi bilgilerinin doğru görüntülendiği kontrol edilir
* Uçuşların kalkış saatleri 10:00 ile 18:00 arası olarak ayarlanır
* Uçuşların kalkış saatlerinin 10:00 ile 18:00 arasında olduğu kontrol edilir

Case 2 - Listelenen uçuşlarda Türk hava yolları uçuşları fiyatlarının sıralamasının artan şekilde gösterilmesi
--------------------------------------------------------------------------------------------------------------
tags: dailyTag
* Ana sayfa görüntülenir
* Bilet tipi olarak uçak seçili olduğu kontrol edilir
* Kalkış noktası için "İstanbul" havaalanı seçilir
* Varış noktası için "Ankara" havaalanı seçilir
* Seyahat tipi için gidiş-dönüş seçilir
* Gidiş tarihi olarak "2024-8-15" seçilir
* "1" saniye beklenir
* Gönüş tarihi olarak "2024-8-22" seçilir
* "1" saniye beklenir
* Seyahat araması yapılır
* Arama detay sayfasında "İstanbul" kalkış "Ankara" varış yeri ve gidiş "departureDateStoreKey" donuş "returnDateStoreKey" tarihi bilgilerinin doğru görüntülendiği kontrol edilir
* Uçuşların kalkış saatleri 10:00 ile 18:00 arası olarak ayarlanır
* Uçuşların kalkış saatlerinin 10:00 ile 18:00 arasında olduğu kontrol edilir
* Uçuş firması olarak "Türk Hava Yolları" seçilir
* Uçuşların fiyatlarının artan şekilde sıralandığı kontrol edilir
* Turk hava yolları sayısı ile uçuşların sayısı eşit olduğu kontrol edilir


Enuygun web sitesinde sıkıntı yaşanırsa impacti en yüksek olabileceğini düşündüğüm yerler
1. Ana sayfa - Uçak/Otel/otobüs arama sayfası
2. Uçak/Otel/otobüs detay sayfası
3. Ödeme sayfası
4. Ödeme başarılı sayfası
5. login sayfası
>>>Uçak için arama ve detay sayasına baktım. ödeme sayfaları için kart bilgi girişler igerekli. Bu yüzden Login sayfası için test senaryosu yazacağım<<<<
Case 3 - Login olma
--------------------------------------------------------------
tags: dailyTag
* Ana sayfa görüntülenir
* Login modalı açılır
* Eposta alanın "enuyguntest@yopmail.com" değeri girilir
* Şifre alanın "123456" değeri girilir
* Giriş Yap butonuna tıklanır
* Başarılı giriş yapıldığı kontrolü yapılır


