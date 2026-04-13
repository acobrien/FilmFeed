package com.example.filmfeed.database

import com.example.filmfeed.models.Movie
import kotlin.arrayOf

class Movies {
    fun getMovies(): List<Movie> {
        return listOf(

            /*
            Movie(
                id = ,
                title = "",
                releaseDate = "",
                genres = arrayOf(),
                overview = "",
                posterPath = "",
                backdropPath = "",

            ),
            */

            Movie(
                id = 1115544,
                title = "Mike & Nick & Nick & Alice",
                releaseDate = "2026-03-14",
                genres = Genres().getGenreNames(arrayOf(35, 878, 80)),
                overview = "Two gangsters and the woman they love try to survive the most dangerous night of their lives. As if that wasn't enough, there's one wild ingredient added to the mix: a time machine.",
                posterPath = "/7F0jc75HrSkLVcvOXR2FXAIwuEv.jpg",
                backdropPath = "/uNToXatdunyvWXyXMrTI1nLvh6r.jpg"
            ),
            Movie(
                id = 1327819,
                title = "Hoppers",
                releaseDate = "2026-03-04",
                genres = Genres().getGenreNames(arrayOf(16, 10751, 878, 35, 12)),
                overview = "Scientists have discovered how to 'hop' human consciousness into lifelike robotic animals, allowing people to communicate with animals as animals. Animal lover Mabel seizes an opportunity to use the technology, uncovering mysteries within the animal world beyond anything she could have imagined.",
                posterPath = "/xjtWQ2CL1mpmMNwuU5HeS4Iuwuu.jpg",
                backdropPath = "/u53UYu5XG2hNgWGvs3xGhAVzypl.jpg"
            ),
            Movie(
                id = 1226863,
                title = "The Super Mario Galaxy Movie",
                genres = Genres().getGenreNames(arrayOf(10751, 35, 12, 14, 16)),
                releaseDate = "2026-04-01",
                overview = "Having thwarted Bowser's previous plot to marry Princess Peach, Mario and Luigi now face a fresh threat in Bowser Jr., who is determined to liberate his father from captivity and restore the family legacy. Alongside companions new and old, the brothers travel across the stars to stop the young heir's crusade.",
                posterPath = "/eJGWx219ZcEMVQJhAgMiqo8tYY.jpg",
                backdropPath = "/kxQiIJ4gVcD3K6o14MJ72p5yRcE.jpg"
            ),
            Movie(
                id = 687163,
                title = "Project Hail Mary",
                genres = Genres().getGenreNames(arrayOf(878, 12)),
                releaseDate = "2026-03-15",
                overview = "Science teacher Ryland Grace wakes up on a spaceship light years from home with no recollection of who he is or how he got there. As his memory returns, he begins to uncover his mission: solve the riddle of the mysterious substance causing the sun to die out. He must call on his scientific knowledge and unorthodox ideas to save everything on Earth from extinction… but an unexpected friendship means he may not have to do it alone.",
                posterPath = "/yihdXomYb5kTeSivtFndMy5iDmf.jpg",
                backdropPath = "/8Tfys3mDZVp4tNoH2ktm06a0Tau.jpg"
            ),
            Movie(
                id = 1290821,
                title = "Shelter",
                genres = Genres().getGenreNames(arrayOf(28, 80, 53)),
                releaseDate = "2026-01-28",
                overview = "A man living in self-imposed exile on a remote island rescues a young girl from a violent storm, setting off a chain of events that forces him out of seclusion to protect her from enemies tied to his past.",
                posterPath = "/buPFnHZ3xQy6vZEHxbHgL1Pc6CR.jpg",
                backdropPath = "/nHxWyy18SvAZ8jJeemtS8k1UNjM.jpg"
            ),
            Movie(
                id = 1171145,
                title = "Crime 101",
                genres = Genres().getGenreNames(arrayOf(80, 53)),
                releaseDate = "2026-02-11",
                overview = "When an elusive thief whose high-stakes heists unfold along the iconic 101 freeway in Los Angeles eyes the score of a lifetime, with hopes of this being his final job, his path collides with a disillusioned insurance broker who is facing her own crossroads. Determined to crack the case, a relentless detective closes in on the operation, raising the stakes even higher.",
                posterPath = "/tVvpFIoteRHNnoZMhdnwIVwJpCA.jpg",
                backdropPath = "/zvGMwQ1mAFrxSzzMFT9WMEl6hxB.jpg"
            ),
            Movie(
                id = 1159559,
                title = "Scream 7",
                genres = Genres().getGenreNames(arrayOf(27, 9648, 80)),
                releaseDate = "2026-02-25",
                overview = "When a new Ghostface killer emerges in the quiet town where Sidney Prescott has built a new life, her darkest fears are realized as her daughter becomes the next target. Determined to protect her family, Sidney must face the horrors of her past to put an end to the bloodshed once and for all.",
                posterPath = "/jjyuk0edLiW8vOSnlfwWCCLpbh5.jpg",
                backdropPath = "/hz7TdCrpLLt2Dz7S3PS2HG9rpAO.jpg"
            )
        )
    }
}