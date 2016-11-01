# Ramanujan Taxi in Scala with Three Optimizations
Implementation of Ramanujan's Taxi by Srinivasa Ramanujan in three different optimizations. 

G.H. Hardy: "I remember once going to see him when he was ill at Putney. I had ridden in taxi cab number 1729 and
remarked that the number seemed to me rather a dull one, and that I hoped it was not an unfavorable omen.
"No," he replied, "it is a very interesting number; it is the smallest number expressible as the sum of two cubes
in two different ways." https://en.wikipedia.org/wiki/1729_(number)

The algorithms are implemented in O(n^4), O(n^3), and O(n^2).

Formula: a^3 + b^3 = c^3 + d^3

Where:
a^3 <= n;
b^3 <= n;
c^3 <= n;
d^3 <= n;
a != c  & a != d;
b != c  & b != d


