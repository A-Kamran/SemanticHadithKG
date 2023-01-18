# Competencey questions for the Semantic Hadith ontology

The given SPARQL are _examples_ that may be reinterpreted and reused for applications.

1. Which Hadith 'containsMentionOf' Verse 11 of Surah 11?
[Run Query](http://115.186.60.94:7200/sparql?name=Which%20Hadith%20'containsMentionOf'%20Verse%2011%20of%20Surah%2011%3F&infer=true&sameAs=true&query=PREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20owl%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0Aselect%20%09%3Fhadith%20%3Fverse%0Awhere%20%7B%20%0A%09%3Fhadith%20rdf%3Atype%20%3AHadith%20.%0A%20%20%20%20%3Fhadith%20%3AcontainsMentionOf%20%3Fverse%0A%7D%20%0AVALUES%20(%3Fverse)%0A%7B%0A%20%20%20%20(%3AV-1111)%0A%7D%0A)
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
select 	?hadith ?verse
where { 
	?hadith rdf:type :Hadith .
    ?hadith :containsMentionOf ?verse
} 
VALUES (?verse)
{
    (:V-1111)
}
```
2. How many hadith were narrated by RAWI_A? [Run Query](http://115.186.60.94:7200/sparql?name=How%20many%20Hadith%20were%20narrated%20by%20Narrator_A&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20(COUNT%20(%3Fname)%20AS%20%3Fnum)%0Awhere%20%0A%7B%20%0A%09%3Fhadith%20rdf%3Atype%20%3AHadith%20.%0A%20%20%20%20%3Fhadith%20%3AhasNarratorChain%20%3Fo%20.%0A%20%20%20%20%3Fo%20%3AhasNarratorSegment%09%20%3Fx%20.%0A%20%20%20%20%3Fx%20%3ArefersToNarrator%2B%09%20%3Fy%20.%0A%20%20%20%20%3Fy%20%3Aname%20%3Fname%0A%20%20%20%20%0A%7D%20%0AVALUES%20(%3Fname)%0A%7B%0A%20%20%20%20(%22%D8%B9%D8%A8%D8%AF%20%D8%A7%D9%84%D9%84%D9%87%20%D8%A8%D9%86%20%D9%8A%D9%88%D8%B3%D9%81%40ar%22)%0A%7)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select (COUNT (?name) AS ?num)
where 
{ 
	?hadith rdf:type :Hadith .
    ?hadith :hasNarratorChain ?o .
    ?o :hasNarratorSegment	 ?x .
    ?x :refersToNarrator+	 ?y .
    ?y :name ?name
    
} 
VALUES (?name)
{
    ("عبد الله بن يوسف@ar")
}
```


3. What is the 'Lineage' of a particular narrator? [Run Query](http://115.186.60.94:7200/sparql?name=What%20is%20the%20'Lineage'%20of%20a%20particular%20narrator%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0A%0Aselect%20%3Flineage%0Awhere%0A%7B%0A%20%20%20%20%3AHN05913%20%3Alineage%20%3Flineage.%0A%7D)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

select ?lineage
where
{
    :HN05913 :lineage ?lineage.
}
```

4. Show if any 2 Narrators share same deathPlace "المدينة"  [Run Query](http://115.186.60.94:7200/sparql?name=Show%20if%20any%202%20Narrators%20share%20same%20deathPlace%20%22%D8%A7%D9%84%D9%85%D8%AF%D9%8A%D9%86%D8%A9%22&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0A%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%20%3Fnarrators%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fnarrators%20rdf%3Atype%20%3AHadithNarrator%20.%0A%20%20%20%20%3Fnarrators%20%3AdeathPlace%20%22%D8%A7%D9%84%D9%85%D8%AF%D9%8A%D9%86%D8%A9%22.%0A%7D%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

select  ?narrators
where 
{ 
    ?narrators rdf:type :HadithNarrator .
    ?narrators :deathPlace "المدينة".
}
```


5. Who is the Root Narrator of a given Hadith?   [Run Query](http://115.186.60.94:7200/sparql?name=Who%20is%20the%20Root%20Narrator%20of%20a%20given%20Hadith%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Froot%20%3Fnarrator%20%3Fname%0Awhere%20%0A%7B%20%0A%20%20%20%20%3ASB-HD0001%20%3AhasNarratorChain%20%3FChain.%20%0A%20%20%20%20%3FChain%20%3AhasRootNarratorSegment%20%3Froot.%0A%20%20%20%20%3Froot%20%3ArefersToNarrator%20%3Fnarrator.%0A%20%20%20%20%3Fnarrator%20%3Aname%20%3Fname%0A%20%20%20%20%0A%7D%20%20%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?root ?narrator ?name
where 
{ 
    :SB-HD0001 :hasNarratorChain ?Chain. 
    ?Chain :hasRootNarratorSegment ?root.
    ?root :refersToNarrator ?narrator.
    ?narrator :name ?name
    
}  
```

6. How many Hadith 'containsMentionOf' Verse X?   [Run Query](http://115.186.60.94:7200/sparql?name=How%20many%20Hadith%20'containsMentionOf'%20Verse%20X%3F&infer=true&sameAs=true&query=%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20owl%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0A%0ASelect%20(count(%3Fhadith)%20as%20%3FnumberOfhadith)%20%0Awhere%20%7B%20%0A%09%3Fhadith%20rdf%3Atype%20%3AHadith%20.%0A%20%20%20%20%3Fhadith%20%3AcontainsMentionOf%20%3Fverse%0A%7D%20%0AVALUES%20(%3Fverse)%0A%7B%0A%20%20%20%20(%3AV-1111)%0A%7D%0A)
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>

Select (count(?hadith) as ?numberOfhadith) 
where { 
	?hadith rdf:type :Hadith .
    ?hadith :containsMentionOf ?verse
} 
VALUES (?verse)
{
    (:V-1111)
}
```

7. Find all Hadith related to Hadith_X?  [Run Query](http://115.186.60.94:7200/sparql?name=Find%20all%20Hadith%20related%20to%20Hadith_X%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fhadith%0Awhere%20%0A%7B%20%0A%20%20%20%20%3ASB-HD0001%20rdf%3AseeAlso%20%3Fhadith.%20%20%0A%7D%20%20)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadith
where 
{ 
    :SB-HD0001 rdf:seeAlso ?hadith.  
}  
```

8. What is the Hadith Type of Hadith_X.   [Run Query](http://115.186.60.94:7200/sparql?name=What%20is%20the%20Hadith%20Type%20of%20Hadith_X.&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3FhadithType%20%0Awhere%20%0A%7B%20%0A%20%20%20%20%3ASB-HD0001%20%3AhasHadithType%20%3FhadithType.%20%0A%7D%20%20%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadithType 
where 
{ 
    :SB-HD0001 :hasHadithType ?hadithType. 
}  
```

9. Mention all Narrators and the RootNarrator for a given Hadith   [Run Query](http://115.186.60.94:7200/sparql?name=Mention%20all%20Narrators%20and%20the%20RootNarrator%20for%20a%20given%20Hadith&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fname%0Awhere%20%0A%7B%20%0A%20%20%20%20%3ASB-HD0001%20%3AhasNarratorChain%20%3FChain.%20%0A%20%20%20%20%3FChain%20%3AhasNarratorSegment%20%3Froot.%0A%20%20%20%20%3Froot%20%3ArefersToNarrator%20%3Fnarrator.%0A%20%20%20%20%3Fnarrator%20%3Aname%20%3Fname%0A%20%20%20%20%0A%7D%20%20)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?name
where 
{ 
    :SB-HD0001 :hasNarratorChain ?Chain. 
    ?Chain :hasNarratorSegment ?root.
    ?root :refersToNarrator ?narrator.
    ?narrator :name ?name
    
}  
```

10. What are the types of Hadith?  [Run Query](http://115.186.60.94:7200/sparql?name=What%20are%20the%20types%20of%20Hadith%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20owl%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0Aselect%20%3FhadithType%0Awhere%20%0A%7B%20%0A%20%20%20%20%3FhadithType%20rdf%3Atype%20%3AHadithType.%0A%20%0A%7D%20)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
select ?hadithType
where 
{ 
    ?hadithType rdf:type :HadithType.
 
} 
```


11. What is the frequency of a specific chain or part of chain i.e. How many times A>B>C>D is repeated.  
<!-- [Run Query]() -->
```

```

12. Is there ANY chain that is repeated more than x times?
 <!-- [Run Query]() -->

```

```

13. Search a hadith where the chain has Narrator_A and Narrator_B but not Narrator_C and matn includes TOPIC_A and LOCATION_B.
<!-- [Run Query]() -->

```

```
14. What is the number of hadith by TOPIC narrated by Narrator_A?
 <!-- [Run Query]() -->

```

```
16. Search a hadith of type 'mauquf' from Narrator_A.  [Run Query](http://115.186.60.94:7200/sparql?name=Search%20a%20hadith%20of%20type%20'mauquf'%20from%20Narrator_A.&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fhadith%20%3Fnarrator%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fhadith%20%3AhasHadithType%20%3Asevered.%20%0A%20%20%20%20%3Fhadith%20%3AhasNarratorChain%20%3Fchain.%0A%20%20%20%20%3Fchain%20%3AhasNarratorSegment%20%3Fnarratorsegment.%0A%20%20%20%20%3Fnarratorsegment%20%3ArefersToNarrator%20%3Fnarrator.%0A%09%3Fnarrator%20%3Aname%20'%D8%B3%D9%84%D9%8A%D9%85%D8%A7%D9%86%20%D8%A8%D9%86%20%D8%AD%D8%B1%D8%A8%20%D8%A8%D9%86%20%D8%A8%D8%AC%D9%8A%D9%84%40ar'.%0A%0A%7D%20%20)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadith ?narrator
where 
{ 
    ?hadith :hasHadithType :severed. 
    ?hadith :hasNarratorChain ?chain.
    ?chain :hasNarratorSegment ?narratorsegment.
    ?narratorsegment :refersToNarrator ?narrator.
	?narrator :name 'سليمان بن حرب بن بجيل@ar'.

}  
```

17. Search for a hadith narrated by someone who lived/died in Medina.  [Run Query](http://115.186.60.94:7200/sparql?name=Search%20for%20a%20hadith%20narrated%20by%20someone%20who%20lived%2Fdied%20in%20Medina.&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fhadith%20%20%3Fnarrator%20%3FnarratorName%20%3Fr%20%3Fd%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fhadith%20%3AhasNarratorChain%20%3FChain.%20%0A%20%20%20%20%3FChain%20%3AhasRootNarratorSegment%20%3Froot.%0A%20%20%20%20%3Froot%20%3ArefersToNarrator%20%3Fnarrator.%0A%20%20%20%20%3Fnarrator%20%3Aname%20%3FnarratorName.%0A%20%20%20%20%3Fnarrator%20%3Aresidence%20%3Fr.%0A%20%20%20%20%3Fnarrator%20%3AdeathPlace%20%3Fd%0A%20%20%20%20Filter%20(%3Fr%20%3D%20'%D8%A7%D9%84%D9%85%D8%AF%D9%8A%D9%86%D8%A9'%20%7C%7C%20%3Fd%20%3D%20'%D8%A7%D9%84%D9%85%D8%AF%D9%8A%D9%86%D8%A9').%0A%7D%20%20%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadith  ?narrator ?narratorName ?r ?d
where 
{ 
    ?hadith :hasNarratorChain ?Chain. 
    ?Chain :hasRootNarratorSegment ?root.
    ?root :refersToNarrator ?narrator.
    ?narrator :name ?narratorName.
    ?narrator :residence ?r.
    ?narrator :deathPlace ?d
    Filter (?r = 'المدينة' || ?d = 'المدينة').
}  
```
18. Find all the hadith narrated from ابن عباس about the topic 'fiqh' (Jurispudence)?   
 <!-- [Run Query]() -->

```

```
19. Find Hadith narrated by Narrator_A    [Run Query](http://115.186.60.94:7200/sparql?name=Find%20Hadith%20narrated%20by%20Narrator_A&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fhadith%20%0A%7B%20%0A%09%3Fhadith%20rdf%3Atype%20%3AHadith%20.%0A%20%20%20%20%3Fhadith%20%3AhasNarratorChain%20%3Fo%20.%0A%20%20%20%20%3Fo%20%3AhasNarratorSegment%09%20%3Fx%20.%0A%20%20%20%20%3Fx%20%3ArefersToNarrator%09%20%3Fy%20.%0A%20%20%20%20%3Fy%20%3Aname%20%3Fname%0A%20%20%20%20%0A%7D%20%0AVALUES%20(%3Fname)%0A%7B%0A%20%20%20%20(%22%D8%B9%D8%A8%D8%AF%20%D8%A7%D9%84%D9%84%D9%87%20%D8%A8%D9%86%20%D9%8A%D9%88%D8%B3%D9%81%40ar%22)%0A%7D)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadith 
{ 
	?hadith rdf:type :Hadith .
    ?hadith :hasNarratorChain ?o .
    ?o :hasNarratorSegment	 ?x .
    ?x :refersToNarrator	 ?y .
    ?y :name ?name
    
} 
VALUES (?name)
{
    ("عبد الله بن يوسف@ar")
}
```

20. How many Hadith are narrated by Narrator_A who heardFrom Narrator_B?   [Run Query](http://115.186.60.94:7200/sparql?name=How%20many%20Hadith%20are%20narrated%20by%20Narrator_A%20who%20heardFrom%20Narrator_B%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0Aselect%20(COUNT%20(%3Fhadith)%20AS%20%3FnumHadith)%0Awhere%0A%7B%0A%20%20%20%20%09%3Fhadith%20%3AhasNarratorChain%20%3Fchain.%0A%09%20%20%20%20%3Fchain%20%3AhasNarratorSegment%20%3Fs.%0A%20%20%20%20%09%3Fs%20%3ArefersToNarrator%20%3AHN03583.%0A%20%20%20%20%09%3AHN03583%20%3AheardFrom%20%3AHN01012.%0A%09%0A%7D)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
select (COUNT (?hadith) AS ?numHadith)
where
{
    	?hadith :hasNarratorChain ?chain.
	    ?chain :hasNarratorSegment ?s.
    	?s :refersToNarrator :HN03583.
    	:HN03583 :heardFrom :HN01012.
	
}
```

21. Who narrated Hadith_X?   [Run Query](http://115.186.60.94:7200/sparql?name=Who%20narrated%20Hadith_X%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Froot%20%3Fnarrator%20%3Fname%0Awhere%20%0A%7B%20%0A%20%20%20%20%3ASB-HD0001%20%3AhasNarratorChain%20%3FChain.%20%0A%20%20%20%20%3FChain%20%3AhasRootNarratorSegment%20%3Froot.%0A%20%20%20%20%3Froot%20%3ArefersToNarrator%20%3Fnarrator.%0A%20%20%20%20%3Fnarrator%20%3Aname%20%3Fname%0A%20%20%20%20%0A%7D%20%20)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?root ?narrator ?name
where 
{ 
    :SB-HD0001 :hasNarratorChain ?Chain. 
    ?Chain :hasRootNarratorSegment ?root.
    ?root :refersToNarrator ?narrator.
    ?narrator :name ?name
    
}  
```
22. Are there any narrators residing in Location_X?   [Run Query](http://115.186.60.94:7200/sparql?name=Are%20there%20any%20narrators%20residing%20in%20Location_X%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0A%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%20%3Fnarrators%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fnarrators%20rdf%3Atype%20%3AHadithNarrator%20.%0A%20%20%20%20%3Fnarrators%20%3Aresidence%20%22%D8%A7%D9%84%D9%85%D8%AF%D9%8A%D9%86%D8%A9%22.%0A%7D%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

select  ?narrators
where 
{ 
    ?narrators rdf:type :HadithNarrator .
    ?narrators :residence "المدينة".
}

```
23. Which Hadith has no 'containsMentionOf' of verse?  
 <!-- [Run Query]() -->

```

```
24. What is the generation of Narrator_A?  [Run Query](http://115.186.60.94:7200/sparql?name=What%20is%20the%20generation%20of%20Narrator_A%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0A%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fgeneration%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fnarrator%20rdf%3Atype%20%3AHadithNarrator%20.%0A%20%20%20%20%3Fnarrator%20%3ApopularName%20'%D8%A3%D8%A8%D9%88%20%D8%A8%D9%83%D8%B1%20%D8%A8%D9%86%20%D8%A3%D8%A8%D9%8A%20%D8%B3%D8%A8%D8%B1%D8%A9%20%D8%A7%D9%84%D9%82%D8%B1%D8%B4%D9%8A'.%0A%20%20%20%20%3Fnarrator%20%3Ageneration%20%3Fgeneration.%0A%7D%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?generation
where 
{ 
    ?narrator rdf:type :HadithNarrator .
    ?narrator :popularName 'أبو بكر بن أبي سبرة القرشي'.
    ?narrator :generation ?generation.
}

```

25. Which Narrators belongs to the first generation of Narrators?  [Run Query](http://115.186.60.94:7200/sparql?name=Which%20Narrators%20belongs%20to%20the%20first%20generation%20of%20Narrators%3F&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0A%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fnarrator%20%3Fname%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fnarrator%20rdf%3Atype%20%3AHadithNarrator%20.%0A%20%20%20%20%3Fnarrator%20%3Aname%20%3Fname.%0A%20%20%20%20%3Fnarrator%20%3Ageneration%20'1'.%0A%7D%0A)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?narrator ?name
where 
{ 
    ?narrator rdf:type :HadithNarrator .
    ?narrator :name ?name.
    ?narrator :generation '1'.
}

```
26. What is the most narrated TOPIC by Narrator_A?
 <!-- [Run Query]() -->

```

```
27. When was Narrator_A born? (Lunar calender)  [Run Query](http://115.186.60.94:7200/sparql?name=When%20was%20Narrator_A%20born%3F%20(Lunar%20calender)&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20%3Fby%0Awhere%20%0A%7B%20%0A%20%20%20%20%3Fnarrator%20rdf%3Atype%20%3AHadithNarrator%20.%0A%20%20%20%20%3Fnarrator%20%3ApopularName%20'%D8%A3%D8%A8%D9%88%20%D8%A8%D9%83%D8%B1%20%D8%A8%D9%86%20%D8%A3%D8%A8%D9%8A%20%D8%B3%D8%A8%D8%B1%D8%A9%20%D8%A7%D9%84%D9%82%D8%B1%D8%B4%D9%8A'.%0A%20%20%20%20%3Fnarrator%20%3AbirthYear%20%3Fby.%0A%7D)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?by
where 
{ 
    ?narrator rdf:type :HadithNarrator .
    ?narrator :popularName 'أبو بكر بن أبي سبرة القرشي'.
    ?narrator :birthYear ?by.
}
```
28. Which narrator narrated the most Hadith?
 <!-- [Run Query]() -->

```

```

29. Two separate Narrator Chains with two same narrators but in different order.  [Run Query](http://115.186.60.94:7200/sparql?name=Two%20separate%20Narrator%20Chains%20with%20two%20same%20narrators%20but%20in%20different%20order.&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20owl%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0Aselect%20*%20where%20%7B%20%0A%09%3Fhadith%20%3AhasNarratorChain%20%3Fchain.%0A%09%3Fchain%20%3AhasNarratorSegment%20%3Fs.%0A%09%3Fs%20%3ArefersToNarrator%20%3AHN08272.%0A%20%20%20%20%3Fs%20%3Aprecedes%20%7C%20%3Afollows%20%3Fs2.%0A%09%3Fs2%20%3ArefersToNarrator%20%3AHN02885.%0A%7D%0A)

```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
select * where { 
	?hadith :hasNarratorChain ?chain.
	?chain :hasNarratorSegment ?s.
	?s :refersToNarrator :HN08272.
    ?s :precedes | :follows ?s2.
	?s2 :refersToNarrator :HN02885.
}

```
30. Visualising Two separate Narrator Chains with two same narrators but in different order.  [Run Query](http://115.186.60.94:7200/sparql?name=Visualising%20Two%20separate%20Narrator%20Chains%20with%20two%20same%20narrators%20but%20in%20different%20order.&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0Aconstruct%20%7B%3Fc1%20%3ArelatedToChain%20%3Fc2%7D%20where%20%7B%20%0A%09%3ASB-HD5292%20%3AhasNarratorChain%20%3Fc1.%0A%09%3ASB-HD2661%20%3AhasNarratorChain%20%3Fc2.%0A%7D)
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
construct {?c1 :relatedToChain ?c2} where { 
	:SB-HD5292 :hasNarratorChain ?c1.
	:SB-HD2661 :hasNarratorChain ?c2.
}
```
<!-- 31. What are the different values of rank of Narrators?
 <!-- [Run Query]() -->

```

``` -->
