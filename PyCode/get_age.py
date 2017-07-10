def get_age_at_wedding(person_id):
    person = get_person_from_db(person_id)
    anniversary = person['anniversary']
    birthday = person['birthday']

    age = anniversary.year - birthday.year
    if birthday.replace(year=anniversary.year):
        age -= 1

    return age
def get_person_from_db(person_id_id):
    raise RuntimeError('the real `get_person_from_db` function'
                'was called.')
