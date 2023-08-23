package com.example.myapplication.screens.movie

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MoviePageBinding
import com.example.myapplication.screens.adapter.AdapterCatalog
import com.example.myapplication.screens.page.EveryItemView
import java.util.Locale

class MoviePage : Fragment() {

    private lateinit var binding: MoviePageBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var pointerBack: ImageView
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView
    private lateinit var adapter: AdapterCatalog
    private lateinit var cardView: ConstraintLayout

    private val listFilters = listOf("Drama", "Comedy", "Horror", "Sci-Fi", "Action", "Adventure")
    private var listMovie = ArrayList<MovieCatalog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviePageBinding.inflate(inflater, container, false)
        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pointerBack = binding.pointerBack
        recyclerView = binding.recyclerView
        searchView = binding.searchView
        cardView = binding.cardView

        spinner = binding.spinner
        searchView.isIconifiedByDefault = false
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        addMovie()
        val genreAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listFilters)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = genreAdapter
        spinner.setSelection(0,false)
        adapter = AdapterCatalog(requireContext(), listMovie)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object  : AdapterCatalog.onItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedMovie = adapter.getItemAtPosition(position)
                val intent = Intent(requireContext(), EveryItemView::class.java)
                val trans : Bundle = ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
                intent.putExtra("movie_name", selectedMovie.name)
                intent.putExtra("movie_image", selectedMovie.intro)
                intent.putExtra("movie_description", selectedMovie.descriptionMovie)
                intent.putExtra("movie_back", selectedMovie.backgroundMode)
                startActivity(intent, trans)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchInList(newText)
                return true
            }
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedGenre = genreAdapter.getItem(position) as String
                filterItemsByGenre(selectedGenre)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun searchInList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<MovieCatalog>()
            for (i in listMovie) {
                if (i.name.contains(query.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Movie found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun filterItemsByGenre(selectedGenre: String) {
        val filteredList = listMovie.filter { movie ->
            movie.genre.contains(selectedGenre)
        }
        adapter.setFilteredList(filteredList)
    }

    fun addMovie(){
        listMovie.add(MovieCatalog("The Shawshank Redemption", listOf("Drama"), R.drawable.shawshank, "142 min","The Shawshank Redemption - Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",null));
        listMovie.add(MovieCatalog("The Godfather", listOf("Crime", "Drama"), R.drawable.godfather, "175 min","The Godfather - The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",null));
        listMovie.add(MovieCatalog("Pulp Fiction", listOf("Crime", "Drama"), R.drawable.pupli, "154 min","Pulp Fiction - The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption. ",null));
        listMovie.add(MovieCatalog("The Dark Knight", listOf("Action", "Crime", "Drama"), R.drawable.thedarkknight, "152 min","The Dark Knight - When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. ",null));
        listMovie.add(MovieCatalog("Fight Club", listOf("Drama"), R.drawable.fightclub, "139 min","Fight Club - An insomniac office worker forms an underground fight club that evolves into something much, much more.",null));
        listMovie.add(MovieCatalog("Inception", listOf("Action", "Adventure", "Sci-Fi"), R.drawable.inception, "148 min","Inception - A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",null));
        listMovie.add(MovieCatalog("Forrest Gump", listOf("Drama", "Romance"), R.drawable.forestgump, "142 min","Forrest Gump - The presidencies of Kennedy to Johnson seen through the eyes of the naive Forrest Gump.",null));
        listMovie.add(MovieCatalog("The Matrix", listOf("Action", "Sci-Fi"), R.drawable.thematrix, "136 min","The Matrix - A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers. ",null));
        listMovie.add(MovieCatalog("Jurassic Park", listOf("Adventure", "Sci-Fi"), R.drawable.parkrecreation, "127 min","Jurassic Park - A pragmatic paleontologist and a chaotic mathematician's team race to survive in an island overrun by dinosaurs.",null));
        listMovie.add(MovieCatalog("Star Wars: Episode IV - A New Hope", listOf("Action", "Adventure", "Fantasy"), R.drawable.starwarsfour, "121 min","Star Wars: Episode IV - A New Hope - Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee, and two droids to save the galaxy from the Empire's world-destroying battle station.",null));
        listMovie.add(MovieCatalog("Titanic", listOf("Drama", "Romance"), R.drawable.titanic, "195 min","Titanic - A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.",null));
        listMovie.add(MovieCatalog("The Lord of the Rings: The Fellowship of the Ring", listOf("Action", "Adventure", "Drama"), R.drawable.lordring, "178 min","The Lord of the Rings: The Fellowship of the Ring - A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",null));
        listMovie.add(MovieCatalog("Avatar", listOf("Action", "Adventure", "Fantasy"), R.drawable.avatar, "162 min","Avatar - A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",null));
        listMovie.add(MovieCatalog("Avengers: Endgame", listOf("Action", "Adventure", "Drama"), R.drawable.avengers, "181 min","Avengers: Endgame - After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more to undo Thanos' actions and restore balance to the universe. ",1));
        listMovie.add(MovieCatalog("The Lion King", listOf("Animation", "Adventure", "Drama"), R.drawable.lionking, "88 min","The Lion King - Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.",null));
        listMovie.add(MovieCatalog("The Terminator", listOf("Action", "Sci-Fi"), R.drawable.terminator, "107 min","The Terminator - A human soldier is sent from 2029 to 1984 to stop an almost indestructible cyborg killing machine, sent from the same year, which has been programmed to execute a young woman whose unborn son is the key to humanity's future salvation. ",null));
        listMovie.add(MovieCatalog("Inglourious Basterds", listOf("Adventure", "Drama", "War"), R.drawable.inglurius, "153 min","Inglourious Basterds - In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theater owner's vengeful plans for the same.",null));
        listMovie.add(MovieCatalog("The Social Network", listOf("Biography", "Drama"), R.drawable.social, "120 min","The Social Network - As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea, and by the co-founder who was later squeezed out of the business.",null));
        listMovie.add(MovieCatalog("Deadpool", listOf("Action", "Adventure", "Comedy"), R.drawable.deadpool, "108 min","Deadpool - A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks. ",1));
        listMovie.add(MovieCatalog("Guardians of the Galaxy", listOf("Action", "Adventure", "Comedy"), R.drawable.galaxy, "121 min","Guardians of the Galaxy - A group of intergalactic criminals is forced to work together to stop a fanatical warrior from taking control of the universe.",1));
        listMovie.add(MovieCatalog("The Revenant", listOf("Action", "Adventure", "Drama"), R.drawable.revenant, "156 min","The Revenant - A frontiersman on a fur trading expedition fights for survival after being mauled by a bear and left for dead by members of his own hunting team. ",null));
        listMovie.add(MovieCatalog("The Departed", listOf("Crime", "Drama", "Thriller"), R.drawable.dep, "151 min","The Departed - An undercover cop and a mole in the police force attempt to identify each other while infiltrating an Irish gang in Boston.",null));
        listMovie.add(MovieCatalog("Interstellar", listOf("Adventure", "Drama", "Sci-Fi"), R.drawable.inter, "169 min","Interstellar - A team of explorers travels through a wormhole in space in an attempt to ensure humanity's survival.",null));
        listMovie.add(MovieCatalog("The Silence of the Lambs", listOf("Crime", "Drama", "Thriller"), R.drawable.silence, "118 min","The Silence of the Lambs - A young F.B.I. cadet must confide in an incarcerated and manipulative killer to receive his help on catching another serial killer who skins his victims.",null));
        listMovie.add(MovieCatalog("The Grand Budapest Hotel", listOf("Adventure", "Comedy", "Crime"), R.drawable.hotel, "99 min","The Grand Budapest Hotel - A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the hotel's glorious years under an exceptional concierge.",null));
        listMovie.add(MovieCatalog("The Shape of Water", listOf("Adventure", "Drama", "Fantasy"), R.drawable.sahpe, "123 min","The Shape of Water - At a top-secret research facility in the 1960s, a lonely janitor forms a unique relationship with an amphibious creature that is being held in captivity.",null));
        listMovie.add(MovieCatalog("The Dark Knight Rises", listOf("Action", "Adventure"), R.drawable.darkk, "164 min","The Dark Knight Rises - Eight years after the Joker's reign of anarchy, Batman, with the help of the enigmatic Catwoman, is forced from his exile to save Gotham City from the brutal guerrilla terrorist Bane. ",null));
        listMovie.add(MovieCatalog("Harry Potter and the Sorcerer's Stone", listOf("Adventure", "Family", "Fantasy"), R.drawable.harry, "152 min","Harry Potter and the Sorcerer's Stone - An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family, and the terrible evil that haunts the magical world. ",null));
        listMovie.add(MovieCatalog("Mad Max: Fury Road", listOf("Action", "Adventure", "Sci-Fi"), R.drawable.mad, "120 min","Mad Max: Fury Road - In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search of her homeland with the help of a group of female prisoners, a psychotic worshiper, and a drifter named Max. ",null));
        listMovie.add(MovieCatalog("The Wolf of Wall Street", listOf("Biography", "Comedy", "Crime"), R.drawable.wolf, "180 min","The Wolf of Wall Street - Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption, and the federal government.",null));
        listMovie.add(MovieCatalog("Finding Nemo", listOf("Animation", "Adventure", "Comedy"), R.drawable.nemo, "100 min","Finding Nemo - After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.",null));
        listMovie.add(MovieCatalog("La La Land", listOf("Comedy", "Drama", "Music"), R.drawable.lala, "128 min","La La Land - While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.",null));
        listMovie.add(MovieCatalog("The Avengers", listOf("Action", "Adventure", "Sci-Fi"), R.drawable.theavenger, "143 min","The Avengers - Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",1));
        listMovie.add(MovieCatalog("The Fast and the Furious", listOf("Action", "Crime", "Thriller"), R.drawable.forsajfirst, "106 min","The Fast and the Furious - Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",null))
        listMovie.add(MovieCatalog("Gladiator", listOf("Action", "Adventure", "Drama"), R.drawable.gladiator, "155 min","Gladiator - A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.",null))
        listMovie.add(MovieCatalog("The Princess Bride", listOf("Adventure", "Family", "Fantasy"), R.drawable.princes, "98 min","The Princess Bride - While home sick in bed, a young boy's grandfather reads him the story of a farmboy-turned-pirate who encounters numerous obstacles, enemies, and allies in his quest to be reunited with his true love.",null));
        listMovie.add(MovieCatalog("The Godfather: Part II", listOf("Crime", "Drama"), R.drawable.godthefather, "202 min","The Godfather: Part II - The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate. ",null));
        listMovie.add(MovieCatalog("Toy Story", listOf("Animation", "Adventure", "Comedy"), R.drawable.toy, "81 min","Toy Story - A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.",null));
        listMovie.add(MovieCatalog("Back to the Future", listOf("Adventure", "Comedy", "Sci-Fi"), R.drawable.back, "116 min","Back to the Future - Marty McFly, a teenager, is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend, eccentric scientist Doc Brown.",null));
        listMovie.add(MovieCatalog("The Hunger Games", listOf("Action", "Adventure", "Sci-Fi"), R.drawable.hunger, "142 min","The Hunger Games - Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games: a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death. ",null));
        listMovie.add(MovieCatalog("Shrek", listOf("Animation", "Adventure", "Comedy"), R.drawable.shrek, "90 min","Shrek - A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.",null));
        listMovie.add(MovieCatalog("The Hangover", listOf("Comedy"), R.drawable.hang, "100 min","The Hangover - A Las Vegas-set comedy centered around three groomsmen who lose their about-to-be-wed buddy during their drunken misadventures, then must retrace their steps to find him.",null));
        listMovie.add(MovieCatalog("Home Alone", listOf("Comedy", "Family"), R.drawable.alone, "103 min","Home Alone - An eight-year-old troublemaker must protect his house from a pair of burglars when he is accidentally left home alone by his family during Christmas vacation. ",null));
        listMovie.add(MovieCatalog("Jaws", listOf("Adventure", "Thriller"), R.drawable.jaws, "124 min","Jaws - A giant great white shark arrives on the shores of a New England beach resort and wreaks havoc with bloody attacks on swimmers until a part-time sheriff teams up with a marine biologist and an old seafarer to hunt the monster down.",null));
        listMovie.add(MovieCatalog("The Notebook", listOf("Drama", "Romance"), R.drawable.noote, "123 min","The Notebook - A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom, but they are soon separated because of their social differences. ",null));
        listMovie.add(MovieCatalog("E.T. the Extra-Terrestrial", listOf("Family", "Sci-Fi"), R.drawable.et, "115 min","E.T. the Extra-Terrestrial - A troubled child summons the courage to help a friendly alien escape Earth and return to his home world.",null));
        listMovie.add(MovieCatalog("The Martian", listOf("Adventure", "Drama", "Sci-Fi"), R.drawable.martian, "144 min","The Martian - An astronaut becomes stranded on Mars after his team assume him dead, and he must rely on his ingenuity to find a way to signal to Earth that he is alive.",null));
        listMovie.add(MovieCatalog("Gravity", listOf("Drama", "Sci-Fi", "Thriller"), R.drawable.gravity, "91 min","Gravity - Two astronauts work together to survive after an accident leaves them stranded in space.",null));
        listMovie.add(MovieCatalog("Black Panther", listOf("Action", "Adventure", "Sci-Fi"), R.drawable.panter, "134 min","Black Panther - T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country's past. ",1));
        listMovie.add(MovieCatalog("Casino Royale", listOf("Action", "Adventure", "Thriller"), R.drawable.casino, "144 min","Casino Royale - After earning 00 status and a license to kill, Secret Agent James Bond sets out on his first mission as 007. Bond must defeat a private banker funding terrorists in a high-stakes game of poker at Casino Royale.",null));
        listMovie.add(MovieCatalog("Spider-Man", listOf("Action", "Adventure"), R.drawable.spiderman, "121 min","Spider-Man - When bitten by a genetically modified spider, a nerdy, shy, and awkward high school student gains spider-like abilities that he eventually must use to fight evil as a superhero after tragedy strikes his family.",2));
        listMovie.add(MovieCatalog("Spider-Man 2", listOf("Action", "Adventure"), R.drawable.spiderman2, "127 min","Spider-Man 2 - Peter Parker struggles to balance his crime-fighting duties as Spider-Man with the demands of his personal life, including his relationship with Mary Jane Watson.",2));
        listMovie.add(MovieCatalog("Spider-Man 3", listOf("Action", "Adventure"), R.drawable.spiderman3, "139 min","Spider-Man 3 - Peter Parker's Spider-Man suit becomes infused with an alien symbiote that turns him into a darker version of himself while dealing with the arrival of two new villains: Sandman and Venom.",2));
        listMovie.add(MovieCatalog("The Amazing Spider-Man", listOf("Action", "Adventure"), R.drawable.amazingspiderman, "136 min","The Amazing Spider-Man - After being bitten by a genetically altered spider, teenager Peter Parker gains newfound spider-like powers and tries to unravel the mystery of his parents' disappearance while battling the villainous Lizard.",3));
        listMovie.add(MovieCatalog("The Amazing Spider-Man 2", listOf("Action", "Adventure"), R.drawable.amazingspiderman2, "142 min","The Amazing Spider-Man 2 - Peter Parker faces new challenges as he battles Electro and the Green Goblin while trying to uncover the truth about his father's past.",3));
        listMovie.add(MovieCatalog("Spider-Man: Homecoming", listOf("Action", "Adventure"), R.drawable.homecoming, "133 min","Spider-Man: Homecoming - Peter Parker tries to balance his life as a high school student and his superhero alter ego, while also facing off against the villainous Vulture.",4));
        listMovie.add(MovieCatalog("Spider-Man: Far From Home", listOf("Action", "Adventure"), R.drawable.farfromhome, "129 min","Spider-Man: Far From Home - Peter Parker goes on a school trip to Europe, where he must team up with Mysterio to stop the Elementals from wreaking havoc across the continent.",4));
    }

    override fun onStart() {
        super.onStart()
        pointerBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
