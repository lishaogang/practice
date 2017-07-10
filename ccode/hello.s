	.file	"hello.c"
	.globl	_f
	.data
	.align 4
_f:
	.long	1
	.def	___main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
LC0:
	.ascii "%d\12\0"
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB10:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	subl	$16, %esp
	call	___main
	movl	_f, %eax
	cmpl	$1, %eax
	jne	L2
	movl	_f, %eax
	subl	$1, %eax
	movl	%eax, _f
	call	_main
	movl	%eax, 4(%esp)
	movl	$LC0, (%esp)
	call	_printf
L2:
	movl	$999, %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE10:
	.ident	"GCC: (GNU) 5.3.0"
	.def	_printf;	.scl	2;	.type	32;	.endef
