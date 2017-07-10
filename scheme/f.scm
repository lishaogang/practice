(define (accumulate op initial sequence)
	(if (null? sequence)
		initial
		(op (car sequence)
			(accumulate op initial (cdr sequence)))))


(define (horner-eval x cofficient-sequence)
		(cond ((null? cofficient-sequence) 0)
				(else (+ (car cofficient-sequence)
						(* x
							(horner-eval x (cdr cofficient-sequence)))))))

(define (advanced-horner-eval x cofficient-sequence)
		(accumulate (lambda (this-coff higher-iterms)
							(+ this-coff (* higher-iterms x)))
					0
					cofficient-sequence))


(define (accumulate-n op initial seqs)
		(if (null? (car seqs))
			'()
			(cons (accumulate op initial (map car seqs))
					(accumulate-n op initial (map cdr seqs)))))


;product of two vector
(define (dot-product v w)
		(accumulate + 0 (map * v w)))

(define (matrix-*-vector m v)
	(map (lambda (mm) (dot-product mm v)) m))


(define (matrix-*-matrix m n)
	(let ((cols (transpose n)))
	(map (lambda (mm) (matrix-*-vector cols mm)) m)))

(define (transpose m)
		(accumulate-n cons '() m))

(define m (list (list 1 2 3) (list 4 5 6) (list 6 7 8)))
(define v (list 1 2 3
))

;(dot-product v v)

;(matrix-*-vector m v)

;(accumulate-n + 0 m)

;(matrix-*-matrix m m)
;(horner-eval 5 (list 1 2 3))
;(advanced-horner-eval 5 (list 1 2 3))





;2017/6/1
(define (make-monitor fun count)
	(define (how-many-calls)
		(display count))
	(define (dispatch m)
		(cond ((eq? m 'how-many-calls) how-many-calls)
				(else
					(begin (set! count (+ 1 count))
							fun))))
	dispatch)

(define monitor-accumulate (make-monitor accumulate 0))

;((monitor-accumulate 'accumulate) + 0 (list 1 2 3))

;((monitor-accumulate 'accumulate) + 0 (list 1 2 4))

;((monitor-accumulate 'how-many-calls))

(define (square-list l)
	(map
		(lambda (x) (* x x))
		l))

;(square-list (list 1 2 3 4))

;2017/6/4
(define (last-pair l)
	(if (null? (cdr l))
		(car l)
		(last-pair (cdr l))))

(define (my-reverse l)
	(if (null? (cdr l))
		l
		(append (my-reverse (cdr l)) (list (car l)))))


;2017/6/5
(define (count-change amount)
	(cc amount 5))

(define (cc amount kinds-of-coins)
	(cond ((= amount 0) 1)
				((or (< amount 0) (= kinds-of-coins 0)) 0)
				(else (+	(cc amount
					 						(- kinds-of-coins 1))
								(cc (- amount
									 			(first-denomination kinds-of-coins))
								 		kinds-of-coins)))))

(define (first-denomination kinds-of-coins)
	(cond ((= kinds-of-coins 1) 1)
				((= kinds-of-coins 2) 5)
				((= kinds-of-coins 3) 10)
				((= kinds-of-coins 4) 25)
				((= kinds-of-coins 5) 50)))
;This is not very good, it would be better if change the (null? list-of-coins) in 3rd line, the 5th line
;and the last line to a function such as (no-more? list-of-coins),(first-denomination list-of-coins)
;and (except-first-denomination list-of-coins) , so this program could be more clear
(define (new-cc amount list-of-coins)
				(cond ((= amount 0) 1)
							((or (< amount 0) (null? list-of-coins)) 0)
							(else (+ (new-cc (- amount
								 									(car list-of-coins))
																list-of-coins)
												(new-cc amount
													 			(cdr list-of-coins))))))
;what should I do if I want call a lambda function in itself?
(define (g fun . args)
				(fun fun args))
(g (lambda (f	 w)  (cond ((null? w) 0)
									(else (+ (car w)
													(f f (cdr w))))))
		 1 2 3 4 5)



;2017/6/6
(define fac
(lambda (f)
	(lambda (x) (cond ((= x 1) 1)
										(else (* x
															(f (- x 1))))))))

(define (fix fun)
	(lambda (x) ((fun	(fix fun)) x)))

((fix (lambda (f)
		     (lambda (x) (cond ((= x 1) 1)
					      					 (else (* x
													    	    (f (- x 1))))))))
	5)

(define add
(lambda (f)
	(lambda (args) (cond ((null? args) 0)
										 (else (+ (car args)
											 				(f (cdr args))))))))

(define (fixed fun)
  (lambda (args) ((fun (fixed fun)) args)))

((fixed add) (list 1 2 3 4 5))


;2017/6/10
;it is important that when you call (cons 1 1) , (cons 1 (list 1)) , (cons (list 1) 1)
;(cons (list 1) (list 1))
(define (remove-last l)
	(if (null? (cdr l))
			'()
			(cons (car l)
						(remove-last (cdr l)))))

(define (remove-last-1 l)
	(define (iter things answer)
		(if (null? (cdr things))
				answer
				(iter (cdr things)
							(append answer
											(list (car things))))))
		(iter (cdr l) (list (car l))))

(define (iter-reverse l)
	(define (iter things answer)
		(if (null? things)
				answer
				(iter (cdr things)
				 			(cons (car things)
										answer))))
 (iter l '()))

(define (square-list items)
	(define (iter things answer)
		(if (null? things)
				answer
				(iter (remove-last things)
							(cons (square (last-pair things))
										answer))))
(iter items '()))


(define (for-each fun l)
	(define (iter fun l call-fun)
		(if (null? l)
				#t
				(iter fun (cdr l) (fun (car l)))))
	(iter fun l #t))

;(for-each (lambda (x) (newline) (display x))
;						(list 1 2 3 4 5))



(define (count-leaves x)
	(cond ((null? x) 0)
				((not (pair? x)) 1)
			(else (+ (count-leaves (car x))
				 			 (count-leaves (cdr x))))))

(define (deep-reverse x)
	(cond ((null? x) '())
				((and (pair? x) (not (pair? (car x)))) (reverse x))
				(else (list (deep-reverse (cdr x))
				 						(deep-reverse (car x))))))

(define (deep-reverse x)
	(map reverse x))

(define (fringe x)
	(cond ((null? x) '())
				((not (pair? x)) (list x))
				(else (append (fringe (car x))
											(fringe (cdr x))))))


(define (epilogue x)
	(cond ((null? x) '())
				((not (pair? x)) (list x))
				(else (append (epilogue (cdr x))
											(epilogue (car x))))))

;2017/6/11
(define (scale-tree tree factor)
	(map (lambda (sub-tree)
					(if (pair? sub-tree)
							(scale-tree sub-tree factor)
							(* sub-tree factor)))
			tree))

;(define (equal? w v)
; 	(if (not (and (pair? w) (pair? v)))
;			(eq? w v)
;			(and (equal? (car w) (car v))
;					 (equal? (cdr w) (cdr v)))))
;(equal? '(this is a list) '(this (is a) list))
;(equal? '(this is a list) '(this is a list))


(define (list->tree elements)
	(car (partial-tree elements (length elements))))

(define (make-tree entry left-branch right-branch)
	(list entry left-branch right-branch))

(define (partial-tree elts n)
	(if (= n 0)
			(cons '() elts)
			(let ((left-size (quotient (- n 0) 2)))
				(let ((left-result (partial-tree elts left-size)))
					(let ((left-tree (car left-result))
							  (no-left-elts (cdr left-result))
								(right-size (- n (+ left-size 1))))
						(let ((right-result (partial-tree (cdr no-left-elts) right-size))
									(this-entry (car no-left-elts)))
								(let ((right-tree (car right-result))
											(remaining-elts (cdr right-result)))
										(cons (make-tree this-entry left-tree right-tree)
													remaining-elts))))))))

;(define l (list 1 3 5 7 9 11 15 48 89))
;(list->tree l)

;2017/6/15
(define (make-queue)	(cons '() '()))

(define (front-ptr queue) (car queue))
(define (rear-ptr queue) (cdr queue))

(define (set-front-ptr! queue item) (set-car! queue item))
(define (set-rear-ptr! queue item) (set-cdr! queue item))

(define (empty-queue? queue) (null? (front-ptr queue)))
(define (front-queue queue)
	(if (empty-queue? queue)
			(error "FRONT called with an empty queue" queue)
			(car (front-ptr queue))))

(define (insert-queue! queue item)
	(let ((new-pair (cons item '())))
		(cond ((empty-queue? queue)
					 (set-front-ptr! queue new-pair)
					 (set-rear-ptr! queue new-pair)
					 queue)
					 (else
						 (set-cdr! (rear-ptr queue) new-pair)
						 (set-rear-ptr! queue new-pair)
						 queue))))

(define (delete-queue! queue)
	(cond ((empty-queue? queue)
				 (error "DELETE! called with an empty queue" queue))
				(else
					(set-front-ptr! queue (cdr (front-ptr queue)))
					queue)))

(define (print-queue queue)
	(display (front-ptr queue))
	'())

(define (make-queue)
	(let ((front-ptr '())
				(rear-ptr '()))
		;...
		(define (set-front-ptr! item) (set! front-ptr item))
		(define (set-rear-ptr! item) (set! rear-ptr item))

		(define (empty-queue?)
			(null? front-ptr))

		(define (front-queue)
			(car front-ptr))

		(define (delete-queue!)
			(cond ((empty-queue?)
					 	 (error "DELETE! called with an empty queue" '()))
						(else
						 (set-front-ptr! (cdr front-ptr))
						 '())))

		(define (insert-queue! item)
			(let ((new-pair (cons item '())))
				(cond ((empty-queue?)
							 (set-front-ptr! new-pair)
							 (set-rear-ptr! new-pair)
							 '())
							 (else
								 (set-cdr! rear-ptr new-pair)
								 (set-rear-ptr! new-pair)
								 '()))))

		(define (print-queue)
		 	(display front-ptr)
		'())
		;...
		(define (dispatch m)
			(cond ((equal? m 'insert-queue!)
						 insert-queue!)
						((equal? m 'delete-queue!)
						 delete-queue!)
						((equal? m 'empty-queue?)
						 empty-queue?)
						((equal? m 'front-queue)
						 front-queue)
						((equal? m 'print-queue)
						 print-queue)
						(else
							(error "Called with an wrong command" '()))))
			dispatch))

;(define exit (exit))

(define q (make-queue))
((q 'insert-queue!) 'a)
((q 'insert-queue!) 'b)
((q 'insert-queue!) 'c)
((q 'insert-queue!) 'd)
((q 'print-queue))
((q 'delete-queue!))
((q 'print-queue))
((q 'front-queue))


;
