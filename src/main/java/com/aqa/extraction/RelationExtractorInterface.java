package com.aqa.extraction;

import com.aqa.kb.Document;
import com.aqa.kb.Triple;

import java.util.ArrayList;

public interface RelationExtractorInterface {

    ArrayList<Triple> extractRelations(int sentenceNumber, String sentence, Document currentDoc);

}
