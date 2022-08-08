package com.example.imdbapp.network.model.person

import com.example.imdbapp.domain.model.Person
import com.example.imdbapp.domain.util.DomainMapper

class PersonDtoMapper : DomainMapper<PersonModel, Person> {
    override fun mapToDomainModel(model: PersonModel): Person {
        return Person(
            awards = model.awards,
            birthDate = model.birthDate,
            deathDate = model.deathDate,
            castMovies = model.castMovies,
            errorMessage = model.errorMessage,
            height = model.height,
            image = model.image,
            id = model.id,
            knownFor = model.knownFor,
            name = model.name,
            role = model.role,
            summary = model.summary,

        )
    }

    override fun mapFromDomainModel(domainModel: Person): PersonModel {
        TODO("Nothing to implement.")
    }

}