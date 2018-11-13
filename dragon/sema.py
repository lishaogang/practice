#!/usr/bin/env python
# -*- coding: utf-8 -*-
import lexer

from util import Production, Symbol, Entry, SYMBOL_TABLE, enter, search_for_symbol


TERMINAL_SET = set()

NON_TERMINAL_SET = set()

SYMBOL_DICT = {}

PRODUCTION_LIST = []

PARSING_TABLE = {}

SEMA_ACTION_TABLE = {}

SYMBOL_STACK = []


LAST_STACK_TOP_SYMBOL = None

CODE_SIZE = 0

CODE_RESULT = []

current_symbol_table_pos = 0
current_symbol_index = 0

CURRENT_CONDITION_NODE = None
#产生式子程序，计算语义信息，中间代码生成
#P11 ~ P15：类型说明符 int float double short long
def P11():
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['type'] = 'int'
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['length'] = 4


def P12():
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['type'] = 'float'
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['length'] = 4


def P13():
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['type'] = 'double'
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['length'] = 8


def P14():
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['type'] = 'short'
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['length'] = 2


def P15():
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['type'] = 'long'
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['length'] = 4

#声明语句，函数或变量
def P21():
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['type'] = \
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.children[0].attr['type']
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.attr['length'] = \
    symbol_for_str(LAST_STACK_TOP_SYMBOL).father.children[0].attr['length']

#声明语句，传递声明语句的语义信息，更新符号表
def P22():
    global current_symbol_table_pos
    global current_symbol_index
    s = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.children[0]
    #将符号插入符号表
    enter(Entry(s.attr['type'], s.attr['length'], s.attr['name']))
    current_symbol_index += 1
    current_symbol_table_pos += s.attr['length']

#标识符声明
def P31():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    f.attr['name'] = f.children[1].lexical_value

#P41~P44，int,float,short,long常数引用
def P41():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    f.attr['type'] = 'int'
    f.attr['value'] = f.children[0].lexical_value


def P42():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    f.attr['type'] = 'float'
    f.attr['value'] = float(f.children[0].lexical_value)


def P43():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    f.attr['type'] = 'short'
    f.attr['value'] = f.children[0].lexical_value


def P44():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    f.attr['type'] = 'long'
    f.attr['value'] = f.children[0].lexical_value


#变量引用
def P51():
    pass

#常数引用
def P52():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father
    f.attr['type'] = f.children[0].attr['type']
    f.attr['value'] = f.children[0].attr['value']

#赋值语句
def P61():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    if len(f.children) < 3:
        f = f.father.father.father.father

    l = f.children[0]
    r = f.children[2]

    fac = f.children[4]

    lv = search_for_symbol(l.lexical_value)
    if lv is None:
        syntax_error('undefined ' + l.lexical_value)
        return

    if lv.type != r.attr['type']:
        syntax_error('type mismatch')
        return

    result = None
    if 'op' in fac.attr:
        if fac.attr['op'] == '+':
            result = f.attr['value'] + fac.attr['factor']

        if fac.attr['op'] == '*':
            result = f.attr['value'] * fac.attr['factor']
    else:
        result = r.attr['value']
    fac.attr = {}

    code_output(lv.name + ' := ' + str(result))


#表达式出现在赋值语句右部
def P62():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father.father.father
    f.attr['type'] = f.children[2].attr['type']
    f.attr['value'] = f.children[2].attr['value']

#表达式不作为复制语句的右部，且作为表达式的左操作数
def P71():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father.father
    f.attr['type'] = f.children[0].attr['type']
    f.attr['value'] = f.children[0].attr['value']

#P72~P73，单目运算符 ++ ， --
def P72():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father.father
    f.attr['type'] = f.children[0].attr['type']
    f.attr['value'] = f.children[0].attr['value'] + 1


def P73():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father.father
    f.attr['type'] = f.children[0].attr['type']
    f.attr['value'] = f.children[0].attr['value'] - 1

#if语句入口
def P81():
    global CURRENT_CONDITION_NODE
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    CURRENT_CONDITION_NODE = f
    e = f.children[2]
    code_output('IF ' + str(e.attr['value']) + ' GOTO ' + str(CODE_SIZE + 2))
    code_output(None)
    f.attr['back'] = CODE_SIZE - 1

#if 语句出口
def P82():
    prev = CURRENT_CONDITION_NODE.attr['back']
    CODE_RESULT[prev] = 'GOTO ' + str(CODE_SIZE)

#while语句入口
def P91():
    global CURRENT_CONDITION_NODE
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father
    CURRENT_CONDITION_NODE = f
    e = f.children[2]
    code_output('IF ' + str(e.attr['value']) + ' GOTO ' + str(CODE_SIZE + 2))
    #while语句出口待回填
    code_output(None)
    f.attr['back'] = CODE_SIZE - 1

#while入口
def P92():
    prev = CURRENT_CONDITION_NODE.attr['back']
    CODE_RESULT[prev] = 'GOTO ' + str(CODE_SIZE + 1)
    code_output('GOTO ' + str(prev - 1))

#P101~P102表达式不作为赋值语句的右部，且为表达式的右操作数
def P101():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father.father.father
    f.attr['op'] = f.children[0].lexical_value
    f.attr['factor'] = f.children[1].attr['value']


def P102():
    f = symbol_for_str(LAST_STACK_TOP_SYMBOL).father.father.father.father
    f.attr['op'] = f.children[0].lexical_value
    f.attr['factor'] = f.children[1].attr['value']


def no_action():
    pass


SEMA_ACTION_TABLE['P11'] = P11
SEMA_ACTION_TABLE['P12'] = P12
SEMA_ACTION_TABLE['P13'] = P13
SEMA_ACTION_TABLE['P14'] = P14
SEMA_ACTION_TABLE['P15'] = P15
SEMA_ACTION_TABLE['P21'] = P21
SEMA_ACTION_TABLE['P22'] = P22
SEMA_ACTION_TABLE['P31'] = P31
SEMA_ACTION_TABLE['P41'] = P41
SEMA_ACTION_TABLE['P42'] = P42
SEMA_ACTION_TABLE['P43'] = P43
SEMA_ACTION_TABLE['P44'] = P44
SEMA_ACTION_TABLE['P51'] = P51
SEMA_ACTION_TABLE['P52'] = P52
SEMA_ACTION_TABLE['P61'] = P61
SEMA_ACTION_TABLE['P62'] = P62
SEMA_ACTION_TABLE['P71'] = P71
SEMA_ACTION_TABLE['P72'] = P72
SEMA_ACTION_TABLE['P73'] = P73
SEMA_ACTION_TABLE['P81'] = P81
SEMA_ACTION_TABLE['P82'] = P82
SEMA_ACTION_TABLE['P91'] = P91
SEMA_ACTION_TABLE['P92'] = P92
SEMA_ACTION_TABLE['P101'] = P101
SEMA_ACTION_TABLE['P102'] = P102

SEMA_ACTION_TABLE['null'] = no_action


def symbol_for_str(string):
    return SYMBOL_DICT[string]


def is_terminal(string):
    return string in TERMINAL_SET


def syntax_error(msg, line=None, row=None):
    if line is None:
        line = lexer.current_line + 1
    if row is None:
        row = lexer.current_row + 1
    print(str(line) + ':' + str(row) + ' Syntax error: ' + msg)
    exit()

#中间代码生成
def code_output(code):
    global CODE_SIZE
    CODE_SIZE += 1
    CODE_RESULT.append(code)


#产生式、词法单元、语法变量读取
def prepare_symbols_and_productions():
    f = open('grammer.txt', 'r')
    lines = f.readlines()
    terminal = False
    production = False
    for l in lines:
        if l.strip() == '*terminals':
            terminal = True
            production = False
            continue
        if l.strip() == '*productions':
            terminal = False
            production = True
            continue
        if l.strip() == '*end':
            break
        if terminal:
            TERMINAL_SET.update([l.strip()])
        if production:
            left = l.split('::=')[0].strip()
            NON_TERMINAL_SET.update([left])

            try:
                right = l.split('::=')[1].strip()
                if right == '':
                    raise IndexError
                p = Production(left, right.split(' '))
            except IndexError:
                p = Production(left, ['null'])

            PRODUCTION_LIST.append(p)

    for s in TERMINAL_SET:
        sym = Symbol(s, sym_type='T')
        SYMBOL_DICT[s] = sym

    for s in NON_TERMINAL_SET:
        sym = Symbol(s, sym_type='N')
        SYMBOL_DICT[s] = sym

#可推导出空的语法变量
def get_nullable():
    """
    Calculate and mark non-terminals found that is nullable(can derive null).
    We do this first, so we can use the result when calculating First and Follow.
    """
    changes = True
    while changes:
        changes = False
        for p in PRODUCTION_LIST:
            if not symbol_for_str(p.left).is_nullable:
                if p.right[0] == 'null':
                    symbol_for_str(p.left).is_nullable = True
                    changes = True
                    continue
                else:
                    right_is_nullable = symbol_for_str(p.right[0]).is_nullable
                    # For X -> Y1 ... YN, Nullable(X) = Nullable(Y1) &
                    # Nullable(Y2) ... & Nullable(YN)
                    for r in p.right[1:]:
                        if r.startswith('P'):
                            continue
                        right_is_nullable = right_is_nullable & symbol_for_str(
                            r).is_nullable

                    if right_is_nullable:
                        changes = True
                        symbol_for_str(p.left).is_nullable = True

#计算first集合
def get_first():
    """
    Calculate First set of each symbol.
    """
    for s in TERMINAL_SET:
        # For each terminal, initialize First with itself.
        sym = SYMBOL_DICT[s]
        sym.first_set = set([s])

    for s in NON_TERMINAL_SET:
        sym = SYMBOL_DICT[s]
        if sym.is_nullable:
            sym.first_set = set(['null'])
        else:
            sym.first_set = set()

    while True:
        first_set_is_stable = True
        for p in PRODUCTION_LIST:
            sym_left = symbol_for_str(p.left)
            if p.right[0] == 'null':
                sym_left.first_set.update(set(['null']))
                continue
            previous_first_set = set(sym_left.first_set)

            for s in p.right:
                # For X -> Y..., First(X) = First(X) U First(Y)
                sym_right = symbol_for_str(s)
                sym_left.first_set.update(sym_right.first_set)
                # For X -> Y1 Y2 ... Yi-1 , if Y1...Yi-1 is all nullable
                # Then First(X) = First(X) U First(Y1) U First(Y2) ...
                if sym_right.is_nullable:
                    continue
                else:
                    break

            if previous_first_set != sym_left.first_set:
                first_set_is_stable = False

        if first_set_is_stable:
            break

#计算follow集合
def get_follow():
    """
    Calculate Follow set of each symbol.
    """
    for s in NON_TERMINAL_SET:
        sym = symbol_for_str(s)
        sym.follow_set = set()

    symbol_for_str('<s>').follow_set.update(set(['#']))

    while True:
        follow_set_is_stable = True
        for p in PRODUCTION_LIST:
            sym_left = symbol_for_str(p.left)
            if sym_left.is_terminal():
                continue
            for s in p.right:
                if s == 'null':
                    continue
                if s.startswith('P'):
                    continue
                if symbol_for_str(s).is_terminal():
                    continue
                current_symbol = symbol_for_str(s)
                previous_follow_set = set(current_symbol.follow_set)
                next_is_nullable = True
                for s2 in p.right[p.right.index(s) + 1:]:
                    if s2.startswith('P'):
                        continue
                    # For X -> sYt, Follow(Y) = Follow(Y) U First(t)
                    next_symbol = symbol_for_str(s2)
                    current_symbol.follow_set.update(next_symbol.first_set)
                    if next_symbol.is_nullable:
                        continue
                    else:
                        next_is_nullable = False
                        break
                if next_is_nullable:
                    # For X -> sYt, if t is nullable, Follow(Y) = Follow(Y) U
                    # Follow(X)
                    current_symbol.follow_set.update(sym_left.follow_set)

                if current_symbol.follow_set != previous_follow_set:
                    follow_set_is_stable = False

        if follow_set_is_stable:
            break

#计算select集合
def get_select():
    """
    Calculate Select set for each production.
    """
    while True:
        select_set_is_stable = True
        for p in PRODUCTION_LIST:
            sym_left = symbol_for_str(p.left)
            previous_select = set(p.select)
            if p.right[0] == 'null':
                # For A -> a, if a is null, Select(i) = Follow(A)
                p.select.update(sym_left.follow_set)
                continue
            sym_right = symbol_for_str(p.right[0])
            # Otherwise, Select(i) = First(a)
            p.select.update(sym_right.first_set)
            # If a is nullable, Select(i) = First(a) U Follow(A)
            if sym_right.is_nullable:
                p.select.update(sym_right.first_set.union(sym_left.follow_set))
            if previous_select != p.select:
                select_set_is_stable = False
        if select_set_is_stable:
            break

#构造预测分析表
def get_parsing_table():
    """
    Calculate parsing table.
    """
    global PARSING_TABLE
    for non_terminal in NON_TERMINAL_SET:
        if non_terminal.startswith('P'):
            continue
        PARSING_TABLE[non_terminal] = {}
        for p in PRODUCTION_LIST:
            if non_terminal == p.left:
                for symbol in p.select:
                    PARSING_TABLE[non_terminal][symbol] = p
        # Calculate SYNC
        for symbol in symbol_for_str(non_terminal).follow_set:
            if is_terminal(symbol):
                try:
                    p = PARSING_TABLE[non_terminal][symbol]
                except KeyError:
                    PARSING_TABLE[non_terminal][symbol] = 'SYNC'

        for symbol in symbol_for_str(non_terminal).first_set:
            if is_terminal(symbol):
                try:
                    p = PARSING_TABLE[non_terminal][symbol]
                except KeyError:
                    PARSING_TABLE[non_terminal][symbol] = 'SYNC'

                    # prettyprint_parsing_table()

#输出预测分析表
def prettyprint_parsing_table():
    for non_terminal in PARSING_TABLE.keys():
        symbol_to_production_list = []
        for symbol in PARSING_TABLE[non_terminal]:
            p = PARSING_TABLE[non_terminal][symbol]
            symbol_to_production = str(symbol) + ':' + str(p)
            symbol_to_production_list.append(symbol_to_production)

        print(non_terminal)
        print(symbol_to_production_list)

#输出符号表
def print_symbol_table():
    symbol_table = open('./output/symbol_table.txt', 'w')
    for t in SYMBOL_TABLE:
        print(t)
        symbol_table.write(str(t) + '\n')
    symbol_table.close()

#输出中间代码
def print_code_result():
    code = open('./output/code.txt','w')
    for r in CODE_RESULT:
        print(str(CODE_RESULT.index(r)) + ': ' + r)
        code.write(str(CODE_RESULT.index(r)) + ': ' + r + '\n')

#读取下一个单词
def next_token():
    r = lexer.scanner()
    while r is None:
        r = lexer.scanner()
    return r

#初始化，读取文法，计算select集，构造预测分析表
def prepare_grammar():
    prepare_symbols_and_productions()
    get_nullable()
    get_first()
    get_follow()
    get_select()
    get_parsing_table()

#调用相应产生式子程序
def do_sema_actions(symbol):
    SEMA_ACTION_TABLE[symbol]()

#采用自顶向下的预测分析发分析LL(1)文法
def do_parsing():
    global LAST_STACK_TOP_SYMBOL
    SYMBOL_STACK.append('#')
    SYMBOL_STACK.append('<s>')

    token_tuple = next_token()
    productions = open('./output/productions.txt', 'w')
    stack = open('./output/stack.txt', 'w')
    while len(SYMBOL_STACK) > 0:
        stack_top_symbol = SYMBOL_STACK[-1]
        while stack_top_symbol == 'null':
            SYMBOL_STACK.pop()
            stack_top_symbol = SYMBOL_STACK[-1]
        #调用产生式子程序，删除该断点
        if stack_top_symbol.startswith('P'):
            do_sema_actions(stack_top_symbol)
            SYMBOL_STACK.pop()
            stack.write(str(SYMBOL_STACK) + '\n')
            continue
        current_token = token_tuple[0]
        if current_token == 'OP' or current_token == 'SEP':
            current_token = token_tuple[1]

        if current_token == 'SCANEOF':
            current_token = '#'

        if stack_top_symbol == 'null':
            LAST_STACK_TOP_SYMBOL = SYMBOL_STACK.pop()
            continue
        #接受
        if stack_top_symbol == '#':
            break
        #推导
        if not is_terminal(stack_top_symbol):
            #查表
            try:
                p = PARSING_TABLE[stack_top_symbol][current_token]
            except KeyError:
                # 出错
                syntax_error('unmatched')
                token_tuple = next_token()
                continue

            if p == 'SYNC':
                # 出错
                syntax_error("sync symbol, recovering")
                LAST_STACK_TOP_SYMBOL = SYMBOL_STACK.pop()
                stack.write(str(SYMBOL_STACK) + '\n')
                productions.write(str(p) + '\n')
                continue

            #记录所用产生式
            stack.write(str(SYMBOL_STACK) + '\n')
            productions.write(str(p) + '\n')
            LAST_STACK_TOP_SYMBOL = SYMBOL_STACK.pop()
            SYMBOL_STACK.extend(reversed(p.right))
            symbol_for_str((LAST_STACK_TOP_SYMBOL)).children = []
            for symbol in p.right:
                if symbol.startswith('P'):
                    symbol_for_str(LAST_STACK_TOP_SYMBOL).children.append(symbol)
                    continue

                if symbol == 'null':
                    continue
                t = symbol_for_str(symbol)
                symbol_for_str(LAST_STACK_TOP_SYMBOL).children.append(t)
                t.father = symbol_for_str(LAST_STACK_TOP_SYMBOL)

        #匹配
        else:
            symbol_for_str(stack_top_symbol).lexical_value = token_tuple[1]
            LAST_STACK_TOP_SYMBOL = SYMBOL_STACK.pop()
            if LAST_STACK_TOP_SYMBOL != token_tuple[0] and LAST_STACK_TOP_SYMBOL != token_tuple[1]:
                exit('')
            #print(token_tuple,LAST_STACK_TOP_SYMBOL)
            stack.write(str(SYMBOL_STACK) + '\n')
            token_tuple = next_token()

    productions.close()
    stack.close()


def main():
    resourceFile = input('请输入源文件路径:')
    import os
    while not os.path.exists(resourceFile):
        resourceFile = input('文件不存在，请输入源文件路径')
    prepare_grammar()
    lexer.read_source_file(resourceFile)
    do_parsing()
    print("SYMBOL TABLE")
    print("------------")
    print_symbol_table()
    print('\n')
    print("CODE")
    print("------------")
    print_code_result()


if __name__ == '__main__':
    main()
