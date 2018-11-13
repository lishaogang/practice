class MTypeError(Exception):
    def __init__(self,err='矩阵不兼容'):
        Exception.__init__(self,err)

    
class Matrix:
    def __init__(self,*rows):
        self.rows = list(rows) if rows is not None else []
        self.n = self.__mmax()
        self.__extend()
        self.m = len(rows)
    
    def __mmax(self):
        m = 0
        for row in self.rows:
            m = max(m,len(row))
        return m
    
    def __update_dim(self):
        self.n = self.__mmax()
        self.m = len(self.rows)

    def __extend(self):
        for row in self.rows:
            for i in range(self.n-len(row)):
                row.append(0)
    
    def print(self):
        print('-'*5*self.n)
        for row in self.rows:
            print(row,'|')
        print('-'*5*self.n)

    def neg(self):
        m = Matrix()
        for i in range(self.m):
            row = []
            for j in range(self.n):
                row.append(-self.rows[i][j])
            m.rows.append(row)
        return m

    def minus(self,other):
        return self.add(self.neg(other))

    def add(self,other):
        if not (self.m == other.m and self.n == other.n):
            raise MTypeError()
        m = Matrix()
        for i in range(self.m):
            row = []
            for j in range(self.n):
                row.append(self.rows[i][j]+other.rows[i][j])
            m.rows.append(row)
        m.__update_dim()
        return m
        
if __name__ == '__main__':
    A = Matrix([1,2,3],[4,5,6])
    B = Matrix([3,200],[0,5,4])
    M = A.add(B)
    A.print()
    B.print()
    M.print()
    M = A.minus(B)
    A.print()
    B.print()
    M.print()
