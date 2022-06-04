package pdfReadProject.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import pdfReadProject.app.dao.entity.Danisman;
import pdfReadProject.app.dao.entity.Juri;
import pdfReadProject.app.dao.entity.Ogrenci;
import pdfReadProject.app.service.model.ProjeContext;

public class PdfOkuyucu {
	
   private final static String basePath = "../pdfReadProject/src/main/resources/static/pdf/";

   public static ProjeContext pdfOkuVeGerekliBilgileriAl(String dosyaAdi) throws IOException {

      File file = new File(basePath + dosyaAdi);
      PDDocument document = PDDocument.load(file);

      PDFTextStripper pdfStripper = new PDFTextStripper();

      pdfStripper.setStartPage(2);
      pdfStripper.setEndPage(3);
      //Proje baþlýðý,dersi, danýþman ve juri bilgileri 2. sayfadan alýnýyor.
      
      String[] satirlar = pdfStripper.getText(document).split("\r\n|\r|\n");
      
      ProjeContext projeContext = new ProjeContext();

      String projeAd = "", projeDers = "", danismanJuriBilgi = "", donemBilgi = "";
      List<String> danismanJuriBilgileri = new ArrayList<String>();
      boolean projeDersAl = false, projeAdAl = false, danismanJuriAl = false;
      
      for(String satirText : satirlar)
      {  
    	  if(projeDersAl) {
    		  
    		  if(!satirText.trim().equals(""))
    			  projeDers += satirText.trim() + " ";
    		  
    		  if(satirText.trim().equals("") && !projeDers.equals("")) {
    			  projeAdAl = true;
    			  projeDersAl = false;
    		  }
    	  }
    	  
    	  if(projeAdAl) {
    		  
    		  if(!satirText.trim().equals(""))
    			  projeAd += satirText.trim() + " ";
    		  
    		  if(satirText.trim().equals("") && !projeAd.equals(""))
    			  projeAdAl = false;    		 
    	  }
    	  
    	  if(satirText.toLowerCase().trim().contains("bölüm"))
    		  projeDersAl = true;
    	  
    	  if(satirText.toLowerCase().trim().contains("dr."))
    		  danismanJuriAl = true;
    	    
    	  if(danismanJuriAl) {
    		  if(!satirText.trim().equals(""))
    			  danismanJuriBilgi += satirText.replace("...", "").trim() + " ";
    		  
    		  if(!danismanJuriBilgi.equals("") && satirText.toLowerCase().trim().contains(".........")) {
    			  danismanJuriBilgileri.add(danismanJuriBilgi);
    			  danismanJuriBilgi = "";
    			  danismanJuriAl = false;
    		  }  
    	  }
    	  
    	  if(satirText.trim().contains("Tarih:")) {
			  int noktaIndexi = satirText.trim().lastIndexOf(".") + 1;
    		  int yil = Integer.parseInt(satirText.trim().substring(noktaIndexi, satirText.length() - 1));
    		  donemBilgi = yil + "-" + (yil + 1) + " Bahar";
    	  }
      }
      
      pdfStripper.setStartPage(4);
      pdfStripper.setEndPage(5);
      //Öðrenci bilgileri 4. sayfadan alýnýyor.
      
      satirlar = pdfStripper.getText(document).split("\r\n|\r|\n");

      String ogrenciBilgi = "";
      List<String> ogrenciBilgileri = new ArrayList<String>();
      boolean ogrenciBilgiAl = false;
      
      for(String satirText : satirlar)
      {
    	  if(satirText.toLowerCase().trim().contains("no:"))
    		  ogrenciBilgiAl = true;
    	  
    	  if(ogrenciBilgiAl) {
    		  
    		  if(satirText.trim().contains("Ýmza:") && !ogrenciBilgi.equals("")) {
     			  ogrenciBilgileri.add(ogrenciBilgi);
    			  ogrenciBilgi = "";
    			  ogrenciBilgiAl = false;
    		  }
    		  
    		  if(ogrenciBilgiAl == true && !satirText.trim().equals(""))
    			  ogrenciBilgi += satirText.trim() + "-";
    	  }
    	  
      }
      
      pdfStripper.setStartPage(8);
      pdfStripper.setEndPage(12);
      //Özet ve Anahtar kelime bilgileri  8 ile 12. sayfalar arasýnda.
      
      satirlar = pdfStripper.getText(document).split("\r\n|\r|\n");

      String ozet = "", anahtarKelime = "";
      boolean ozetAl = false, anahtarKelimeAl = false;
      
      for(String satirText : satirlar)
      {
    	  if(ozetAl) {
    		  
    		  if(satirText.toLowerCase().trim().contains("anahtar kelimeler") && !ozet.equals("")) {
    			  ozetAl = false;
    			  anahtarKelimeAl = true;
    		  }
    		  
    		  if(!satirText.trim().equals("") && !satirText.trim().contains("Anahtar kelimeler"))
    			  ozet += satirText.trim() + " ";
    	  }
    	  
    	  if(anahtarKelimeAl) {
    		  if(!satirText.trim().equals(""))
    			  anahtarKelime += satirText.trim().replace("Anahtar kelimeler: ", "") + " ";
    		  else
    			  anahtarKelimeAl = false;
    	  }
    	  
    	  
    	  if(satirText.toLowerCase().trim().equals("özet"))
    		  ozetAl = true;
    	  
      }
      
      projeContext.getProje().setProjeAdi(projeAd.trim());
      projeContext.getProje().setProjeDers(projeDers.trim());
      projeContext.getProje().setProjeOzet(ozet.trim());
      projeContext.getProje().setProjeAnahtarKelime(anahtarKelime.replace(", ",";").replace(",",";").replace(".","").trim());
      projeContext.getProje().setProjeDonem(donemBilgi.trim());
      
      projeContext.setJuriler(jurileriAl(danismanJuriBilgileri));
      projeContext.setDanismanlar(danismanlariAl(danismanJuriBilgileri));
      projeContext.setOgrenciler(ogrencileriAl(ogrenciBilgileri));
      
      document.close();
      
      return projeContext;
   }


   private static List<Juri> jurileriAl(List<String> danismanJuriBilgileri) {

	   List<Juri> juriList = new ArrayList<Juri>();
	   
	   for(String str : danismanJuriBilgileri) {
	   
		   if(str.contains("Jüri")) {
			   
			   str = str.split(",")[0].replace("Üyesi", "").replace("Jüri", "").trim();
			   
			   Juri juri = new Juri();
			   
			   int noktaIndexi = str.lastIndexOf(".") + 1;
			   
			   juri.setJuriUnvan(str.substring(0,noktaIndexi).trim());
			   juri.setJuriAdSoyad(str.substring(noktaIndexi,str.length() - 1).trim());
			 
			   juriList.add(juri);
		   }   
	   }
	   
	   return juriList;
   }
   
   private static List<Danisman> danismanlariAl(List<String> danismanJuriBilgileri) {

	   List<Danisman> danismanList = new ArrayList<Danisman>();
	   
	   for(String str : danismanJuriBilgileri) {
	   
		   if(str.contains("Danýþman")) {
			   
			   str = str.split(",")[0].replace("Üyesi", "").replace("Danýþman", "").trim();
			   
			   Danisman danisman = new Danisman();
			   
			   int noktaIndexi = str.lastIndexOf(".") + 1;
			   
			   danisman.setDanismanUnvan(str.substring(0,noktaIndexi).trim());
			   danisman.setDanismanAdSoyad(str.substring(noktaIndexi,str.length() - 1).trim());
			 
			   danismanList.add(danisman);
		   }   
	   }
	   
	   return danismanList;
   }
   
   private static List<Ogrenci> ogrencileriAl(List<String> ogrenciBilgileri) {
	  
	   List<Ogrenci> ogrenciList = new ArrayList<Ogrenci>();
	   
	   for(String str : ogrenciBilgileri) {
		 
		   String[] strArr = str.split("-");
		   
		   String[] ogrNoArr = strArr[0].split(":");
		   String[] ogrAdSoyadArr = strArr[1].split(":");
		 
		   Ogrenci ogrenci = new Ogrenci();	   
		   ogrenci.setOgrNo(ogrNoArr[1].trim());
		   ogrenci.setOgrAdSoyad(ogrAdSoyadArr[1].trim());
		   ogrenci.setOgrOgrenimTuru("Lisans");
		   
		   ogrenciList.add(ogrenci);
	   }
	   
	   return ogrenciList;
   }
   
}