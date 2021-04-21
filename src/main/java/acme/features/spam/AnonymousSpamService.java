package acme.features.spam;

import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnonymousSpamService implements AbstractListService<Anonymous, Spam> {

    @Autowired
    AnonymousSpamRepository spam;

    @Override
    public boolean authorise(Request<Spam> request) {
        assert  request != null;
        return true;
    }

    @Override
    public void unbind(Request<Spam> request, Spam entity, Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        request.unbind(entity, model, "word");
    }

    public List<String> removeSpaces(List<String> s){
        List<String> result = new ArrayList<>();
        for(int i=0; i<s.size();i++){
            String a = s.get(i).replace(" ", "");
            result.add(a);
        }
        return result;
    }
    public boolean isItSpam(String phrase){
        boolean result;
        List<String> spamWords = spam.findMany().stream().map(x-> x.getWord()).collect(Collectors.toList());
        List<String> spamList = removeSpaces(spamWords); // con esto tenemos todas las palabras spam sin espacio
        List<String> wordList = new ArrayList<String>(Arrays.asList(phrase.split(" "))); // con esto tenemos todas las palabras de la frase dividida
        Double threshold=0.;
        for(String a : wordList){ //empezamos a comparar una a una las palabras de la frase con las de la lista de spam
            String c = a.toLowerCase();
            for(String b: spamList){
                String d = b.toLowerCase();
                if(c.equals(d)){ //si la palabra coincide con una de la lista de spam
                    threshold += ((1./wordList.size())*100); //le sumamos al threshold su tanto % correspondiente conforme a la longitud de la frase
                }else {
                    threshold += 0.;
                }
            }
        }
        if(threshold<=10.){
            result = false;
        }else{
            result= true;
        }
        return result;
    }



    @Override
    public Collection<Spam> findMany(Request<Spam> request) {
        assert request != null;
        Collection<Spam> result;
        result = this.spam.findMany();
        return result;
    }
}
