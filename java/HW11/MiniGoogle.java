/* 
 * File: MiniGoogle.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 12/07/2016
 * Purpose: This class implements a mini search engine using hashtables and cosine similarity
 */

import java.util.*;


public class MiniGoogle {
    
    private static final String [] blackList = { "the", "of", "and", "a", "to", "in", "is", 
        "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", 
        "his", "they", "i", "at", "be", "this", "have", "from", "or", "one", 
        "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", 
        "your", "can", "said", "there", "use", "an", "each", "which", "she", 
        "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", 
        "then", "them", "these", "so", "some", "her", "would", "make", "like", 
        "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", 
        "number", "no", "way", "could", "people",  "my", "than", "first", "water", 
        "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", 
        "did", "get", "come", "made", "may", "part" }; // black list of common strings
    
    private static Article[] getArticleList(DatabaseIterator db) {
        
        // count how many articles are in the directory
        int numArticles = db.getNumArticles(); 
        
        // now create array to return list of articles
        Article[] list = new Article[numArticles];
        for(int i = 0; i < numArticles; i++)
            list[i] = db.next();
        
        return list; 
    }
    
    private static DatabaseIterator setupDatabase(String path) {
        return new DatabaseIterator(path);
    }
    
    private static void addArticle(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Add an article");
        System.out.println("==============");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        System.out.println("You may now enter the body of the article.");
        System.out.println("Press return two times when you are done.");
        
        String body = "";
        String line = "";
        do {
            line = s.nextLine();
            body += line + "\n";
        } while (!line.equals(""));
        
        D.insert(new Article(title, body));
    }
    
    
    private static void removeArticle(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Remove an article");
        System.out.println("=================");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        
        D.delete(title);
    }
    
    private static void titleSearch(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Search by article title");
        System.out.println("=======================");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        Article a = D.lookup(title);
        if(a != null)
            System.out.println(a);
        else {
            System.out.println("Article not found!"); 
            return; 
        }
        
        System.out.println("Press return when finished reading.");
        s.nextLine();
    }
    
    private static void contentSearch(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Search by article content");
        System.out.println("=======================");
        
        System.out.print("Enter search phrase: ");
        String keys = s.nextLine();
        
        System.out.println(phraseSearch(keys, D));
        
        System.out.println("Press return when finished reading.");
        s.nextLine();
    }
    
    private static String preprocess(String s) {
        return s.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");
    }
    
    private static boolean blacklisted(String s) {
        for (int i = 0; i < blackList.length; i++) {
            if (s.equals(blackList[i])) {
                return true;
            }
        }
        
        return false;
    } 
    
    private static double getCosineSimilarity(String s, String t) {
        String[] a = preprocess(s).split(" ");
        String[] b = preprocess(t).split(" ");
        TermFrequencyTable L = new TermFrequencyTable();
        for (int i = 0; i < a.length; i++) {
            if (!blacklisted(a[i])) {
                L.insert(a[i], 0);
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (!blacklisted(b[i])) {
                L.insert(b[i], 1);
            }
        }
        return L.cosineSimilarity();
    }
    
    public static String phraseSearch(String phrase, ArticleTable T) {
        ArticleHeap H = new ArticleHeap();
        Iterator<Article> it = T.iterator();
        while (it.hasNext()) {
            Article art = it.next();
            double cs = getCosineSimilarity(phrase, art.getBody());
            art.putCS(cs);
            if (cs > 0.001) H.insert(art);
        }
        String result = "";
        int count = 0;
        try {
            Article res1 = H.getMax();
            result += "Match 1 with cosine similarity of " + res1.getCS() + ":\n\n" + res1 + "\n";
            count++;
        } catch (HeapUnderflowException e) {
            return "There are no matching articles.";
        }
        try {
            Article res2 = H.getMax();
            result += "Match 2 with cosine similarity of " + res2.getCS() + ":\n\n" + res2 + "\n";
            count++;
        } catch (HeapUnderflowException e) {
            return "Top " + count + " Matches:\n\n" + result;
        }
        try {
            Article res3 = H.getMax();
            result += "Match 3 with cosine similarity of " + res3.getCS() + ":\n\n" + res3 + "\n";
            count++;
        } catch (HeapUnderflowException e) {
            return "Top " + count + " Matches:\n\n" + result;
        }
        return "Top " + count + " Matches:\n\n" + result;
    }
    
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        
        String dbPath = "articles/";
        
        DatabaseIterator db = setupDatabase(dbPath);
        
        System.out.println("Read " + db.getNumArticles() + 
                           " articles from disk.");
        
        ArticleTable L = new ArticleTable(); 
        Article[] A = getArticleList(db);
        L.initialize(A);
        
        int choice = -1;
        do {
            System.out.println();
            System.out.println("Welcome to Mini-Google!");
            System.out.println("=====================");
            System.out.println("Make a selection from the " +
                               "following options:");
            System.out.println();
            System.out.println("    1. add a new article");
            System.out.println("    2. remove an article");
            System.out.println("    3. Search by article title");
            System.out.println("    4. Search by phrase (list of keywords)");
            System.out.println();
            
            System.out.print("Enter a selection (1-4, or 0 to quit): ");
            
            choice = user.nextInt();
            user.nextLine();
            
            switch (choice) {
                case 0:
                    return;
                    
                case 1:
                    addArticle(user, L);
                    break;
                    
                case 2:
                    removeArticle(user, L);
                    break;
                    
                case 3:
                    titleSearch(user, L);
                    break;
                    
                case 4:
                    contentSearch(user, L);
                    break;
                    
                default:
                    break;
            }
            
            choice = -1;
            
        } while (choice < 0 || choice > 4);
    } 
}