# Competencey questions for the Semantic Hadith ontology

The given SPARQL are _examples_ that may be reinterpreted and reused for applications.

1. Which Hadith 'containsMentionOf' Verse 1 of Surah 111?
[Run Query](http://44.213.163.148:7200/sparql?savedQueryName=1.%20Which%20Hadith%20'containsMentionOf'%20Verse%201%20of%20Surah%20111%3F&owner=admin)
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
select 	?hadith ?verse
where { 
	?hadith rdf:type :Hadith .
    ?hadith :containsMentionOf ?verse
} 
VALUES (?verse)
{
    (:CH111V1)
}
```

1.1 Hadith Contains Mention of Quranic Verse
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantichadith.com/ontology/>
select * where { 
	?s rdf:type :Hadith.
    ?s :containsMentionOf ?v.
} limit 100 
```
 <!-- 
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantichadith.com/ontology/>
construct 
{ 
    ?s rdf:type :Hadith.
    ?s :containsMentionOf ?v.
    ?v rdf:type :Verse.
}
where { 
	?s rdf:type :Hadith.
    ?s :containsMentionOf ?v.
} limit 100 
--> 
<img width="1107" alt="image" src="https://github.com/A-Kamran/SemanticHadithKG/assets/97387765/9c63ac0b-5e74-40e8-9c8e-ff07eb2b895a">


2. How many hadith were narrated by RAWI_A? [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=2.%20How%20many%20hadith%20were%20narrated%20by%20RAWI_A%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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


3. What is the 'Lineage' of a particular narrator? [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=3.%20What%20is%20the%20'Lineage'%20of%20a%20particular%20narrator%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>

select ?lineage
where
{
    :HN05913 :lineage ?lineage.
}
```

4. Show if any 2 Narrators share same deathPlace "المدينة"  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=4.%20Show%20if%20any%202%20Narrators%20share%20same%20deathPlace%20%22%D8%A7%D9%84%D9%85%D8%AF%D9%8A%D9%86%D8%A9%22&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>

select  ?narrators
where 
{ 
    ?narrators rdf:type :HadithNarrator .
    ?narrators :deathPlace "المدينة".
}
```


5. Who is the Root Narrator of a given Hadith?   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=5.%20Who%20is%20the%20Root%20Narrator%20of%20a%20given%20Hadith%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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

6. How many Hadith 'containsMentionOf' Verse X?   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=6.%20How%20many%20Hadith%20'containsMentionOf'%20Verse%20X%3F&owner=admin)
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>

Select (count(?hadith) as ?numberOfhadith) 
where { 
	?hadith rdf:type :Hadith .
    ?hadith :containsMentionOf ?verse
} 
VALUES (?verse)
{
    (:CH111_V001)
}
```

7. Find all Hadith related to Hadith_X?  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=7.%20Find%20all%20Hadith%20related%20to%20Hadith_X%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadith
where 
{ 
    :SB-HD0001 rdf:seeAlso ?hadith.  
}  
```

8. What is the Hadith Type of Hadith_X.   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=8.%20What%20is%20the%20Hadith%20Type%20of%20Hadith_X.&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?hadithType 
where 
{ 
    :SB-HD0001 :hasHadithType ?hadithType. 
}  
```



9. Mention all Narrators and the RootNarrator for a given Hadith   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=9.%20Mention%20all%20Narrators%20and%20the%20RootNarrator%20for%20a%20given%20Hadith%20&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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

10. What are the types of Hadith?  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=10.%20What%20are%20the%20types%20of%20Hadith%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
select ?hadithType
where 
{ 
    ?hadithType rdf:type :HadithType.
 
} 
```


11. What is the frequency of a specific chain or part of chain i.e. How many times A->B->C is repeated.  
<!-- [Run Query]() -->
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantichadith.com/ontology/>

PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
select ?n1 ?n2 ?n3 (count(distinct ?h) as ?noofhadith) 
where {
    ?h :hasNarratorChain/:hasNarratorSegment ?ns1.
    ?ns1 :refersToNarrator ?n1.
    ?ns1 :precedes/:refersToNarrator ?n2.
    ?ns1 :precedes/:precedes/:refersToNarrator ?n3.
    #?n1 :narratorID "4396"^^xsd:integer.
    #?n2 :narratorID "4903"^^xsd:integer.
    #?n3 :narratorID "7272"^^xsd:integer.
    ?n1 :popularName ?pname1.
    ?n2 :popularName ?pname2.
    ?n3 :popularName ?pname3.
} group by ?n1 ?n2 ?n3

```

12. Is there ANY chain that is repeated more than x times?
 <!-- [Run Query]() -->

```

```

13. Find the number of hadith where the chain has Narrator A and Narrator B but not Narrator C and matn includes TOPIC A.
<!-- [Run Query]() -->

```
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
select ?noofhadith {  
?b :name "The Book of Commentary@en".
{select  ?b (count(?h) as ?noofhadith) where { 
    ?h :isPartOfChapter/:isPartOfBook ?b.
	?h :hasNarratorChain/:hasNarratorSegment/:refersToNarrator ?n1.
    ?h :hasNarratorChain/:hasNarratorSegment/:refersToNarrator ?n2.
    ?n1 rdf:type :HadithNarrator.
    ?n1 :narratorID "4698"^^xsd:integer.
    ?n2 rdf:type :HadithNarrator.
    ?n2 :narratorID "3443"^^xsd:integer.
    ?n1 :name ?name1.
    ?n2 :name ?name2.
    
    FILTER NOT EXISTS {?h :hasNarratorChain/:hasNarratorSegment/:refersToNarrator ?n3.}
    ?n3 rdf:type :HadithNarrator.
    ?n3 :narratorID "989"^^xsd:integer.
    ?n3 :name ?name3.
        } Group by ?b}
    
}


```
14. What is the number of hadith by TOPIC narrated by Narrator_A?
 <!-- [Run Query]() -->

```
PREFIX : <http://www.semantichadith.com/ontology/>
select ?topic (count(?h) as ?noofhadith)
where { 
	?h :hasNarratorChain ?nc.
    ?h :isPartOfChapter/:isPartOfBook ?b.
    ?b :hadithBookIntro ?topic.
    ?nc :hasRootNarratorSegment ?ns.
    ?ns :refersToNarrator :HN04049.
   } group by ?b ?topic
```
16. Search a hadith of type 'mauquf' from Narrator_A.  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=16.%20Search%20a%20hadith%20of%20type%20'mauquf'%20from%20Narrator_A.&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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

17. Search for a hadith narrated by someone who lived/died in Medina.  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=17.%20Search%20for%20a%20hadith%20narrated%20by%20someone%20who%20lived%2Fdied%20in%20Medina.&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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
18. Find all the hadith narrated from ابن عباس about the topic 'Hajj' (Pilgrimmage)?   
 <!-- [Run Query]() -->

```
PREFIX : <http://www.semantichadith.com/ontology/>

select ?h 
where {
	?h :hasNarratorChain ?nc.
    ?h :isPartOfChapter/:isPartOfBook ?b.
    ?b :name ?bname.
    ?nc :hasRootNarratorSegment ?ns.
    ?ns :refersToNarrator :HN04883.
    FILTER REGEX (?bname, "The Book of Hajj@en").
   }

```
19. Find Hadith narrated by Narrator_A    [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=19.%20Find%20Hadith%20narrated%20by%20Narrator_A&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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

20. How many Hadith are narrated by Narrator_A who heardFrom Narrator_B?   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=20.%20How%20many%20Hadith%20are%20narrated%20by%20Narrator_A%20who%20heardFrom%20Narrator_B%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
select (COUNT (?hadith) AS ?numHadith)
where
{
    	?hadith :hasNarratorChain ?chain.
	    ?chain :hasNarratorSegment ?s.
    	?s :refersToNarrator :HN03583.
    	:HN03583 :heardFrom :HN01012.
	
}
```

21. Who narrated Hadith_X?   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=21.%20Who%20narrated%20Hadith_X%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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
22. Are there any narrators residing in Location_X?   [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=22.%20Are%20there%20any%20narrators%20residing%20in%20Location_X%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>

select  ?narrators
where 
{ 
    ?narrators rdf:type :HadithNarrator .
    ?narrators :residence "المدينة".
}

```
23. Which Hadith has no 'containsMentionOf' of verse?  
 <!-- [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=23.%20Which%20Hadith%20has%20no%20'containsMentionOf'%20of%20verse%3F&owner=admin) -->

```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantichadith.com/ontology/>

SELECT ?s WHERE { 
    ?s rdf:type :Hadith.
    MINUS { ?s :containsMentionOf ?v. }
}

```
24. What is the generation of Narrator_A?  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=24.%20What%20is%20the%20generation%20of%20Narrator_A%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select ?generation
where 
{ 
    ?narrator rdf:type :HadithNarrator .
    ?narrator :popularName 'أبو بكر بن أبي سبرة القرشي'.
    ?narrator :generation ?generation.
}

```

25. Which Narrators belongs to the first generation of Narrators?  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=25.%20Which%20Narrators%20belongs%20to%20the%20first%20generation%20of%20Narrators%3F&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>

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
<!--PREFIX : <http://www.semantichadith.com/ontology/>

PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
select ?bname (count(?h) as ?noofhadith)
where { 
	?h :hasNarratorChain ?nc.
    ?h :isPartOfChapter/:isPartOfBook ?b.
    ?b :name ?bname.
    ?nc :hasRootNarratorSegment ?ns.
    ?ns :refersToNarrator :HN04049.
    ?n :name ?name.
    ?n :popularName ?pname.
}	group by ?bname
ORDER BY DESC(?noofhadith)
limit 1-->
```
PREFIX : <http://www.semantichadith.com/ontology/>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
select ?bname (count(?h) as ?noofhadith)
where { 
    ?h :isPartOfChapter/:isPartOfBook ?b.
    ?b :name ?bname.
    FILTER REGEX (?bname, "@ar").
    ?h :hasNarratorChain/:hasRootNarratorSegment/:refersToNarrator :HN04049.    
}	group by ?bname
ORDER BY DESC(?noofhadith)
Limit 1

```
27. When was Narrator_A born? (Lunar calender)  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=27.%20When%20was%20Narrator_A%20born%3F%20(Lunar%20calender)&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
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
PREFIX : <http://www.semantichadith.com/ontology/>

select ?pname ?n (count(distinct ?h) as ?noofhadith)  
where { 
	?h :hasNarratorChain ?nc.
    ?nc :hasRootNarratorSegment ?ns.
    ?ns :refersToNarrator ?n.
    ?n :name ?name.
    ?n :popularName ?pname.
} group by ?pname ?n
ORDER BY DESC(?noofhadith)
limit 1

```

29. Two separate Narrator Chains with two same narrators but in different order.  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=29.%20Two%20separate%20Narrator%20Chains%20with%20two%20same%20narrators%20but%20in%20different%20order.&owner=admin)

```
PREFIX : <http://www.semantichadith.com/ontology/>
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
30. Visualising Two separate Narrator Chains with two same narrators but in different order.  [Run Query](http://44.213.163.148:7200/sparql?savedQueryName=30.%20Visualising%20Two%20separate%20Narrator%20Chains%20with%20two%20same%20narrators%20but%20in%20different%20order.&owner=admin)
```
PREFIX : <http://www.semantichadith.com/ontology/>
construct {?c1 :relatedToChain ?c2} where { 
	:SB-HD5292 :hasNarratorChain ?c1.
	:SB-HD2661 :hasNarratorChain ?c2.
}
```
![image](https://github.com/A-Kamran/SemanticHadithKG/assets/97387765/77dd8d2c-d91f-4cfe-af86-9278cde01b21)

