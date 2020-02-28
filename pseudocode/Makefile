CC = g++
OBJS = flipflop.o textmatch.o
CFLAGS=-c -Wall -O2
PROGRAM = flipflop textmatch

ALL: flipflop textmatch

flipflop: flipflop.o
	$(CC) flipflop.o -o flipflop

textmatch: textmatch.o
	$(CC) textmatch.o -o textmatch

flipflop.o: flipflop.cpp
	$(CC) flipflop.cpp -o flipflop.o $(CFLAGS)

textmatch.o: textmatch.cpp
	$(CC) textmatch.cpp -o textmatch.o $(CFLAGS)

clean:
	@rm -f $(OBJS)
	@rm -f $(PROGRAM)