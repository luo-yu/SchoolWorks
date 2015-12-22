# Super simple makefile

all:
	gcc -m32 -g -O0 -fno-stack-protector -o bufdemo bufdemo.c

asm: all
	objdump -d bufdemo > bufdemo.s

clean: 
	rm -f bufdemo bufdemo.s
