Use this file to record your reflection on this assignment.

I consulted with Rachel Davison and Binah the TA about this assignment. Working with Rachel helped me get the motivation to proceed and made me realize that not all implimentations are pretty. Their code and mine had fundamentally different approaches, with me using a lot of built-ins, while they did a bunch of looping. It made me understand how different approaches can fit with different languages. Mine feels fundamentally python-ish, while theirs might come from Java as a first language.

I attended TA hours on 9/23 to talk with Binah and get a second opinion.

Java feels very fiddly, but mainly I have the impression that all of the 'common' Python objects, especially dict and list, are harder to use. If only I knew the equivilent of 'in'!
Also, not knowing regex is really obnoxious. I hope we learn that soon. I will look into it more independently, but still I think it is something that really needs working on.

It seems like programming generally speaking has a lot of loops and boolean operations, as well as typing. Types like strings and int are common for expressions, and iterables also exist, but can be varying degrees of accessible. Grouping is always nessesary and fiddly, just in different ways. Comments and docstrings or similar are also common.

Using find and replace failed to function, as did using a list of pairs (2-item lists) to do replacing words in a list of words split. What did end up working was learning how to use a dictionary and going back to my wordlist idea (which others also had and mentoned in class) with them. Split did not like splitting at . and ? because regex, so I had to use a double \ and I needed a space on my split spacer so the first letter was not a space. Also, only close the Scanner at the end. Otherwise, it throws an error!

In terms of advice: watch out for things that say regex and be careful. If there is an error with a regex thing, try looking up how to get the literal. Dad taught me this one, actually, when I was having issues with ?. Remember you can google errors. Be persistent. Take breaks if need be. If you want a specific tool, don't try to makeshift it, instead look up how to properly use it. An empty print could mean null or could be "". Try considering both. In general, google is your best friend. 

Also, I wound up getting "null null null null null are null null" as one intermediary on the way to debugging mirror(). It was so funny! Rachel and I both laughed out loud.