using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Fixed_point
{
    class Program
    {
        static Func<T, TResult> Fix<T, TResult>(Func<Func<T, TResult>, Func<T, TResult>> g)
        {
            return x => g(Fix(g))(x);
        }

        static void Main(string[] args)
        {
            var fac = Fix<int, int>(f => x => x <= 1 ? 1 : x * f(x - 1));
            Console.WriteLine(fac(5));
        }
    }
}
