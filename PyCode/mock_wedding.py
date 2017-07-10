import unittest
import sys
from datetime import date
from unittest import mock
from get_age import *
import get_age
class Tests(unittest.TestCase):
    def test_get_age_at_wedding(self):
        module = sys.modules[__name__]
        with mock.patch.object(get_age, 'get_person_from_db') as m:
            m.return_value = {'anniversary': date(2012, 4, 21),
                        'birthday': date(1986, 6, 15)}
            age = get_age_at_wedding(person_id = 42)
            self.assertEqual(age, 25)
