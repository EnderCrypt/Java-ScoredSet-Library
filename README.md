# Java-ScoredSet-Library

This is a java library which contains what i call: "ScoredSet"

a scored set works similar to LinkedList and TreeSet combined
the ScoredList allows you to add objects that implement Scoreable
and sorts them in the order that the objects tell (by returning a score, inwhich the objects with highest scores get placed first and the objects with lowest scores get placed furthest back)

what makes this diffrent from the TreeSet is that ScoredSet allows multiple objects (duplicates if you may) with the same score

an effective example of what this could be used for is scoreboard
