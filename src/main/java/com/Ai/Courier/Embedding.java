//package com.Ai.Courier;
//
//
//import org.apache.poi.hsmf.datatypes.Chunks;
//
//import org.springframework.ai.document.Document;
//import org.springframework.ai.reader.tika.TikaDocumentReader;
//import org.springframework.ai.transformer.splitter.TokenTextSplitter;
//import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class Embedding implements CommandLineRunner {
//
//
//    PineconeVectorStore pineconeVectorStore;
//
//
//    @Value("classpath:/docs/terms.txt")
//    Resource termsOfService;
//
//    public Embedding(PineconeVectorStore pineconeVectorStore) {
//        this.pineconeVectorStore = pineconeVectorStore;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//   System.out.println("EMBEDDING STARTED");
//
//     var pdfReader=new TikaDocumentReader(termsOfService);
//        System.out.println("CHECK 2");
//     TokenTextSplitter textSplitter=new TokenTextSplitter();
//
//     List<Document> chunks=textSplitter.apply(pdfReader.get());
//
//        System.out.println("CHECK 3");
//
//        try {
//            pineconeVectorStore.accept(chunks);
//        }
//            catch(Exception e){
//
//           e.printStackTrace();
//           e.getMessage();
//
//        }
//
//        System.out.println("EMBEDDING DONE");
//
//    }
//}
