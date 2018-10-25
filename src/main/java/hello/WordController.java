package hello;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {
	private static Logger logger = Logger.getLogger(WordController.class);
	static final int NO_OF_CHARS = 256; 
    // Implement the /words/{word} endpoint
	
	@GetMapping("/words/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> checkPallindrome(@PathVariable("word") String word){
		logger.info("Method execution start. input is : " + word);
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		boolean flag = false;
		boolean canFormPallindrome = false;
		if(word!=null) {
			String originalWord = word;
			
			String reversedWord = new StringBuffer(originalWord).reverse().toString();
			if(reversedWord.equals(originalWord)) {
				flag = true;
				logger.info("Both string are equal");
			}
			canFormPallindrome = canFormPalindrome(originalWord);
			
			jsonMap.put("word", word);
			jsonMap.put("pallindrome", flag);
			jsonMap.put("anagramOfPallindrome", canFormPallindrome);
		}
		logger.info("method execution end");
		
		return jsonMap;
	}
	
	static boolean canFormPalindrome(String str) 
    { 
        
        int[] count = new int[NO_OF_CHARS]; 
        for (int i = 0; i < str.length(); i++) 
            count[str.charAt(i)]++; 
  
        int odd = 0; 
        for (int i = 0; i < NO_OF_CHARS; i++) { 
            if ((count[i] & 1) != 0) 
                odd++; 
  
            if (odd > 1) 
                return false; 
        } 
  
        return true; 
    } 
}
