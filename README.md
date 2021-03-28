# Lana Del Rey Song Generator
## Warning: Some of the songs contain explicit language and/or mature themes. Use this song generator at your own risk.

This program generates random Lana Del Rey songs, based on the songs from Born to Die — The Paradise Edition and Ultraviolence. For inspiration, I used some code I had written
for a project I completed in 2016: https://www2.cs.duke.edu/courses/fall16/compsci201/homework.html (see Project 1: Markov for details). I implemented a Markov chain to generate
a song structure based on the song structures in the 2 albums, and then a similar model to generate song lyrics for each verse. The lyrics modeler does not take the probability
of the next word(s) into account, as I wanted to generate funnier lyrics. Each verse contains a randomly generated number of words— this number will always be between the
minimum and maximum number of words of each verse of the same type (e.g. the chorus).

Features I would like to add:
- A front-end component
- Ability to generate songs by other artists
- Word/line repetition
- Verse structure

Please note:
- Refrains, interludes, etc. are all included in the verse type "Misc" as they occur too infrequently to be in categories of their own
- This is a work in progress/VERY rough draft!!

# Credits
Lyrics retrieved from genius.com and adapted to fit the nature of this project.
## Born to Die — The Paradise Edition
Album licensed to Polydor Ltd. (UK) & Interscope Records
### Born to Die
- Produced by Emile Haynie
- Written by Lana Del Rey & Justin Parker
### Off to the Races
- Produced by Emile Haynie & Patrik Berger
- Written by Lana Del Rey & Tim Larcombe
### Blue Jeans
- Produced by Emile Haynie
- Written by Dan Heath, Lana Del Rey & Emile Haynie
### Video Games
- Produced by Sterling Fox & Robopop
- Written by Lana Del Rey & Justin Parker
### Diet Mountain Dew
- Produced by Jeff Bhasker & Emile Haynie
- Written by Lana Del Rey & Mike Daly
### National Anthem
- Produced by Emile Haynie
- Written by Penny Foster, James Bauer-Mein, David Sneddon, Justin Parker & Lana Del Rey
### Dark Paradise
- Produced by Rick Nowels & Emile Haynie
- Written by Lana Del Rey & Rick Nowels
### Radio
- Produced by Emile Haynie
- Written by Lana Del Rey & Justin Parker
### Carmen
- Produced by Emile Haynie
- Written by Lana Del Rey & Justin Parker
### Million Dollar Man
- Produced by Emile Haynie & Chris Braide
- Written by Lana Del Rey & Chris Braide
### Summertime Sadness
- Produced by Emile Haynie
- Written by Lana Del Rey & Rick Nowels
### This Is What Makes Us Girls
- Produced by Emile Haynie & Al Shux
- Written by Lana Del Rey, Jim Irvin & Tim Larcombe
### Without You
- Produced by Emile Haynie
- Written by Emile Haynie, Sacha Skarbek & Lana Del Rey
### Lolita
- Produced by Emile Haynie
- Written by Hannah Robinson, Lana Del Rey & Liam Howe
### Lucky Ones
- Produced by Emile Haynie
- Written by Lana Del Rey & Rick Nowels
### Ride
- Produced by Rick Rubin
- Written by Rick Rubin, Lana Del Rey & Justin Parker
### American
- Produced by Rick Nowels
- Written by Lana Del Rey, Emile Haynie & Rick Nowels
### Cola
- Produced by Rick Nowels
- Written by Lana Del Rey & Rick Nowels
### Body Electric
- Produced by Rick Nowels & Dan Heath
- Written by Lana Del Rey & Rick Nowels
### Blue Velvet
- Produced by Emile Haynie
- Written by Bernie Wayne & Lee Morris
### Gods & Monsters
- Produced by Tim Larcombe
- Written by Lana Del Rey & Tim Larcombe
### Yayo (Paradise Version)
- Produced by Emile Haynie & Dan Heath
- Written by Lana Del Rey
### Bel Air
- Produced by Dan Heath
- Written by Dan Heath & Lana Del Rey
## Ultraviolence
Album licensed to UMG Recordings
### Cruel World
- Produced by Dan Auerbach
- Written by Dan Auerbach, Lana Del Rey & Blake Stranathan
### Ultraviolence
- Produced by Dan Auerbach
- Written by Lana Del Rey & Dan Heath
### Shades of Cool
- Produced by Dan Auerbach
- Written by Lana Del Rey & Rick Nowels
### Brooklyn Baby
- Produced by Dan Auerbach
- Written by Lana Del Rey & Barrie O’Neill
### West Coast
- Produced by Dan Auerbach
- Written by Lana Del Rey & Rick Nowels
### Sad Girl
- Produced by Dan Auerbach
- Written by Lana Del Rey & Rick Nowels
### Pretty When You Cry
- Produced by Lana Del Rey, Blake Stranathan & Lee Foster
- Written by Lana Del Rey & Blake Stranathan
### Money Power Glory
- Produced by Greg Kurstin
- Written by Lana Del Rey & Greg Kurstin
### Fucked My Way Up to the Top
- Produced by Dan Auerbach
- Written by Lana Del Rey & Dan Heath
### Old Money
- Produced by Dan Heath
- Written by Robbie Fitzsimmons, Lana Del Rey & Dan Heath
### The Other Woman
- Produced by Dan Auerbach
- Written by Jessie Mae Robinson
### Black Beauty
- Produced by Paul Epworth
- Written by Lana Del Rey & Rick Nowels
### Guns and Roses
- Produced by Lee Foster, Lana Del Rey & Rick Nowels
- Written by Rick Nowels & Lana Del Rey
### Florida Kilos
- Produced by Dan Auerbach
- Written by Harmony Korine, Lana Del Rey & Dan Auerbach
### Is This Happiness
- Produced by Rick Nowels
- Written by Rick Nowels & Lana Del Rey
### Flipside
- Produced by Blake Stranathan & Lana Del Rey
- Written by Blake Stranathan & Lana Del Rey
