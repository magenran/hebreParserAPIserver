package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;
import vohmm.application.SimpleTagger3;
import vohmm.corpus.Sentence;
import vohmm.corpus.TokenExt;
import yg.chunker.TaggerBasedHebrewChunker;
import yg.sentence.MeniTaggeedSentenceFactory;
import yg.sentence.MeniTokenExpander;

import java.util.ArrayList;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import com.google.gson.Gson;

import hebrewNER.NERTagger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-21T08:07:49.055Z")
public class DefaultApiServiceImpl extends DefaultApiService {
    SimpleTagger3 tagger;
    NERTagger nerTagger;
    //MeniTaggeedSentenceFactory sentenceFactory;
    String path = "serverResorces\\";
    String chunkModelPrefix = path + vohmm.util.Dir.CHUNK_MODEL_PREF;
    MeniTaggeedSentenceFactory sentenceFactory = new MeniTaggeedSentenceFactory(null, MeniTokenExpander.expander);
    Gson gson = new Gson();
    TaggerBasedHebrewChunker chunker  = null;
    
    public DefaultApiServiceImpl(){
    	 try {
			chunker = new TaggerBasedHebrewChunker(sentenceFactory, chunkModelPrefix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @Override
    public Response rootPost(String text, SecurityContext securityContext) throws NotFoundException {

             vohmm.corpus.Sentence sentence = null;

                 Sentence res = null;
             	try {
                 if (text.length() > 0 && chunker!= null) {
                     List taggedSentences;
				
						taggedSentences = tagger.getTaggedSentences(text);
                     if (taggedSentences.size() > 0) {
                         sentence = (vohmm.corpus.Sentence) taggedSentences.get(0);
                         //res = new Sentence(strRes);
                         // Named-entiry recognition for the given tagged sentence
							nerTagger.addNerLabels(sentence);
                     }
                 }else{
                	throw new Exception("chunker exception");
                 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "server error:" + e)).build();
				}

                     

                 List<Word> wordsTokens = new ArrayList<Word>();
                for(TokenExt t:sentence.getTokens()){
                    wordsTokens.add(new Word(t));
                 }

                 String jsonObj =  gson.toJson(wordsTokens);
                 return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, jsonObj)).build();
              } 
     
    }

