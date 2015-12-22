# Super simple makefile

all:
	gcc -m32 -g -O0 -fno-stack-protector -o oflow_echo oflow_echo.c

asm: all
	objdump -d oflow_echo > oflow_echo.s

clean: 
	rm -f oflow_echo oflow_echo.s
