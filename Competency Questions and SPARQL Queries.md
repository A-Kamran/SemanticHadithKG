# Competencey questions for the Semantic Hadith ontology

The given SPARQL are _examples_ that may be reinterpreted and reused for applications.

1. Which Hadith 'containsMentionOf' Verse 11 of Surah 11?
[Query](http://115.186.60.94:7200/sparql?name=How%20many%20Hadith%20were%20narrated%20by%20Narrator_A&infer=true&sameAs=true&query=PREFIX%20%3A%20%3Chttp%3A%2F%2Fwww.i-knex.com%2Fontology%2Fhadith%23%3E%0APREFIX%20rdf%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0Aselect%20(COUNT%20(%3Fname)%20AS%20%3Fnum)%0Awhere%20%0A%7B%20%0A%09%3Fhadith%20rdf%3Atype%20%3AHadith%20.%0A%20%20%20%20%3Fhadith%20%3AhasNarratorChain%20%3Fo%20.%0A%20%20%20%20%3Fo%20%3AhasNarratorSegment%09%20%3Fx%20.%0A%20%20%20%20%3Fx%20%3ArefersToNarrator%2B%09%20%3Fy%20.%0A%20%20%20%20%3Fy%20%3Aname%20%3Fname%0A%20%20%20%20%0A%7D%20%0AVALUES%20(%3Fname)%0A%7B%0A%20%20%20%20(%22%D8%B9%D8%A8%D8%AF%20%D8%A7%D9%84%D9%84%D9%87%20%D8%A8%D9%86%20%D9%8A%D9%88%D8%B3%D9%81%40ar%22)%0A%7)
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
2. How many hadith were narrated by RAWI_A?
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


3. What is the 'Lineage' of a particular narrator? 
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

select ?lineage
where
{
    :HN05913 :lineage ?lineage.
}
```

4. Show if any 2 Narrators share same deathPlace "المدينة"
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>

select  ?narrators
where 
{ 
    ?narrators rdf:type :HadithNarrator .
    ?narrators :deathPlace "المدينة".
}
```


5. Who is the Root Narrator of a given Hadith?
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

6. How many Hadith 'containsMentionOf' Verse X? 
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

7. Find all Hadith related to Hadith_X?
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadith
where 
{ 
    :SB-HD0001 rdf:seeAlso ?hadith.  
}  
```

8. What is the Hadith Type of Hadith_X.
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadithType 
where 
{ 
    :SB-HD0001 :hasHadithType ?hadithType. 
}  
```

9. Mention all Narrators and the RootNarrator for a given Hadith 
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

10. What are the types of Hadith?
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
```

```

12. Is there ANY chain that is repeated more than x times?
```

```

13. Search a hadith where the chain has Narrator_A and Narrator_B but not Narrator_C and matn includes TOPIC_A and LOCATION_B.
```

```
14. What is the number of hadith by TOPIC narrated by Narrator_A?
```

```
16. Search a hadith of type 'mauquf' from Narrator_A.
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

17. Search for a hadith narrated by someone who lived/died in Medina.
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
```

```
19. Find Hadith narrated by Narrator_A
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

20. How many Hadith are narrated by Narrator_A who heardFrom Narrator_B? 
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

21. Who narrated Hadith_X?
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
22. Are there any narrators residing in Location_X?
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
```

```
24. What is the generation of Narrator_A? 
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

25. Which Narrators belongs to the first generation of Narrators?
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
```

```
27. When was Narrator_A born? (Lunar calender)
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
```

```

29. Two separate Narrator Chains with two same narrators but in different order.

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
30. Visualising Two separate Narrator Chains with two same narrators but in different order.
```
PREFIX : <http://www.i-knex.com/ontology/hadith#>
construct {?c1 :relatedToChain ?c2} where { 
	:SB-HD5292 :hasNarratorChain ?c1.
	:SB-HD2661 :hasNarratorChain ?c2.
}
```
<!-- 31. What are the different values of rank of Narrators?
```

``` -->
