package com.example.myapplication.screens.anime

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
import com.example.myapplication.databinding.FragmentSecondButtonBinding
import com.example.myapplication.screens.adapter.AdapterAnimeCatalog
import com.example.myapplication.screens.page.EveryItemView
import java.util.Locale


class AnimePage : Fragment() {

    private lateinit var binding: FragmentSecondButtonBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var pointerBack: ImageView
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView
    private lateinit var adapter: AdapterAnimeCatalog
    private lateinit var cardView: ConstraintLayout

    private val listFilters = listOf("Drama", "Comedy", "Horror", "Sci-Fi", "Action", "Adventure")
    private var listAnime = ArrayList<AnimeCatalog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondButtonBinding.inflate(inflater, container, false)
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
        val genreAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listFilters)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = genreAdapter
        spinner.setSelection(0,false)
        adapter = AdapterAnimeCatalog(requireContext(), listAnime)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object  : AdapterAnimeCatalog.onItemClickListener {
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
            val filteredList = ArrayList<AnimeCatalog>()
            for (i in listAnime) {
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
        val filteredList = listAnime.filter { movie ->
            movie.genre.contains(selectedGenre)
        }
        adapter.setFilteredList(filteredList)
    }

    private fun addMovie(){
        listAnime.add(
            AnimeCatalog(
                "Hunter x Hunter",
                listOf("Adventure", "Action", "Fantasy"),
                R.drawable.hunter_x_hunter,
                "6 seasons",
                "A young boy joins the Hunter Association to find his father, a legendary Hunter, and goes on adventures to pass the Hunter Examination.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Neon Genesis Evangelion",
                listOf("Action", "Psychological", "Mecha"),
                R.drawable.neon_genesis_evangelion,
                "1 season",
                "Teenagers pilot giant mechs to protect Earth from mysterious beings known as Angels.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Cowboy Bebop",
                listOf("Action", "Sci-Fi", "Space"),
                R.drawable.cowboy_bebop,
                "1 season",
                "Bounty hunters travel through space in the year 2071 to catch the galaxy's most dangerous criminals.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "One Punch Man",
                listOf("Action", "Comedy", "Superpower"),
                R.drawable.one_punch_man,
                "2 seasons",
                "Saitama, a hero who can defeat any opponent with a single punch, seeks a worthy adversary.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Steins;Gate",
                listOf("Thriller", "Sci-Fi", "Drama"),
                R.drawable.steins_gate,
                "1 season",
                "A group of friends accidentally create a time machine and must prevent a dystopian future.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Black Clover",
                listOf("Action", "Magic", "Fantasy"),
                R.drawable.black_clover,
                "Ongoing",
                "A boy born without magic in a world where it's everything aims to become the Wizard King.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Code Geass",
                listOf("Action", "Mecha", "Sci-Fi"),
                R.drawable.code_geass,
                "2 seasons",
                "A young prince gains the power of absolute obedience and leads a rebellion against a mighty empire.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Fairy Tail",
                listOf("Adventure", "Action", "Magic"),
                R.drawable.fairy_tail,
                "9 seasons",
                "Members of the Fairy Tail guild take on various magical missions while forming strong bonds.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Demon Slayer: Kimetsu no Yaiba",
                listOf("Action", "Demons", "Supernatural"),
                R.drawable.demon_slayer_kimetsu_no_yaiba,
                "1 season",
                "A boy becomes a demon slayer to avenge his family and save his sister who has been turned into a demon.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Tokyo Ghoul",
                listOf("Action", "Horror", "Supernatural"),
                R.drawable.tokyo_ghoul,
                "2 seasons",
                "A college student is transformed into a half-ghoul after a chance encounter with one of these flesh-eating beings.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Attack on Titan",
                listOf("Action", "Fantasy", "Drama"),
                R.drawable.attack_on_titan,
                "4 seasons",
                "In a world dominated by giant humanoid creatures, humanity fights for survival within enormous walled cities.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Fullmetal Alchemist: Brotherhood",
                listOf("Adventure", "Fantasy", "Action"),
                R.drawable.fullmetal_alchemist_brotherhood,
                "1 season",
                "Two brothers use alchemy in their quest to find the Philosopher's Stone and restore their bodies after a failed attempt to bring their mother back to life.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "One Piece",
                listOf("Adventure", "Action", "Comedy"),
                R.drawable.one_piece,
                "Ongoing",
                "Monkey D. Luffy and his pirate crew search for the legendary One Piece treasure to become the Pirate King.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Death Note",
                listOf("Thriller", "Mystery", "Supernatural"),
                R.drawable.death_note,
                "1 season",
                "A high school student discovers a supernatural notebook that allows him to kill anyone by writing their name while picturing their face.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "My Hero Academia",
                listOf("Action", "Superpower", "School"),
                R.drawable.my_hero_academia,
                "6 seasons",
                "In a world where people have superpowers known as 'Quirks,' a young boy without powers enrolls in a prestigious hero academy.", null
            )
        )






        listAnime.add(
            AnimeCatalog(
                "Bleach",
                listOf("Action", "Adventure", "Fantasy"),
                R.drawable.bleach,
                "2 seasons",
                "Bleach : Substitute Soul Reaper Ichigo Kurosaki spends his days fighting against Hollows, dangerous evil spirits that threaten Karakura Town. Ichigo carries out his quest with his closest allies: Orihime Inoue, his childhood friend with a talent for healing; Yasutora Sado, his high school classmate with superhuman strength; and Uryuu Ishida, Ichigo's Quincy rival.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Kaguya-sama wa Kokurasetai",
                listOf("Comedy", "Romance"),
                R.drawable.kagua,
                "3 seasons",
                "Kaguya-sama wa Kokurasetai : The elite members of Shuchiin Academy's student council continue their competitive day-to-day antics. Council president Miyuki Shirogane clashes daily against vice-president Kaguya Shinomiya, each fighting tooth and nail to trick the other into confessing their romantic love. Kaguya struggles within the strict confines of her wealthy, uptight family, rebelling against her cold default demeanor as she warms to Shirogane and the rest of her friends.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Gintama",
                listOf("Action", "Comedy", "Sci-Fi"),
                R.drawable.gintama,
                "4 seasons",
                "Gintama : Gintoki, Shinpachi, and Kagura return as the fun-loving but broke members of the Yorozuya team! Living in an alternate-reality Edo, where swords are prohibited and alien overlords have conquered Japan, they try to thrive on doing whatever work they can get their hands on. However, Shinpachi and Kagura still haven't been paid... Does Gin-chan really spend all that cash playing pachinko?", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Monster",
                listOf("Drama", "Mystery", "Suspense"),
                R.drawable.monster,
                "1 season",
                "Monster : Dr. Kenzou Tenma, an elite neurosurgeon recently engaged to his hospital director's daughter, is well on his way to ascending the hospital hierarchy. That is until one night, a seemingly small event changes Dr. Tenma's life forever. While preparing to perform surgery on someone, he gets a call from the hospital director telling him to switch patients and instead perform life-saving brain surgery on a famous performer. His fellow doctors, fiancée, and the hospital director applaud his accomplishment; but because of the switch, a poor immigrant worker is dead, causing Dr. Tenma to have a crisis of conscience.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Your Name",
                listOf("Action", "Superpower", "School"),
                R.drawable.yourname,
                "1 episode",
                "Your name : Mitsuha Miyamizu, a high school girl, yearns to live the life of a boy in the bustling city of Tokyo—a dream that stands in stark contrast to her present life in the countryside. Meanwhile in the city, Taki Tachibana lives a busy life as a high school student while juggling his part-time job and hopes for a future in architecture.", null
            )
        )



        listAnime.add(
            AnimeCatalog(
                "Bocchi the Rock!",
                listOf("Comedy"),
                R.drawable.bocci,
                "1 season",
                "Bocchi the Rock : Yearning to make friends and perform live with a band, lonely and socially anxious Hitori \"Bocchi\" Gotou devotes her time to playing the guitar. On a fateful day, Bocchi meets the outgoing drummer Nijika Ijichi, who invites her to join Kessoku Band when their guitarist, Ikuyo Kita, flees before their first show. Soon after, Bocchi meets her final bandmate—the cool bassist Ryou Yamada.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Vinland Saga",
                listOf("Action", "Adventure", "Drama"),
                R.drawable.vinland,
                "2 seasons",
                "Vinland Saga : Young Thorfinn grew up listening to the stories of old sailors that had traveled the ocean and reached the place of legend, Vinland. It's said to be warm and fertile, a place where there would be no need for fighting—not at all like the frozen village in Iceland where he was born, and certainly not like his current life as a mercenary. War is his home now. Though his father once told him, \"You have no enemies, nobody does. There is nobody who it's okay to hurt,\" as he grew, Thorfinn knew that nothing was further from the truth.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Jujutsu Kaisen",
                listOf("Action", "Fantasy"),
                R.drawable.juju,
                "2 seasons",
                "Jujutsu Kaisen : Idly indulging in baseless paranormal activities with the Occult Club, high schooler Yuuji Itadori spends his days at either the clubroom or the hospital, where he visits his bedridden grandfather. However, this leisurely lifestyle soon takes a turn for the strange when he unknowingly encounters a cursed item. Triggering a chain of supernatural occurrences, Yuuji finds himself suddenly thrust into the world of Curses—dreadful beings formed from human malice and negativity—after swallowing the said item, revealed to be a finger belonging to the demon Sukuna Ryoumen, the \"King of Curses.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Oshi no Ko",
                listOf("Drama", "Supernatural"),
                R.drawable.starchild,
                "1 season",
                "Oshi no Ko : In the entertainment world, celebrities often show exaggerated versions of themselves to the public, concealing their true thoughts and struggles beneath elaborate lies. Fans buy into these fabrications, showering their idols with undying love and support, until something breaks the illusion. Sixteen-year-old rising star Ai Hoshino of pop idol group B Komachi has the world captivated; however, when she announces a hiatus due to health concerns, the news causes many to become worried.", null
            )
        )


        listAnime.add(
            AnimeCatalog(
                "Great Teacher Onizuka",
                listOf("Comedy"),
                R.drawable.teacher,
                "1 season",
                "Great Teacher Onizuka : Twenty-two-year-old Eikichi Onizuka—ex-biker gang leader, conqueror of Shonan, and virgin—has a dream: to become the greatest high school teacher in all of Japan. This isn't because of a passion for teaching, but because he wants a loving teenage wife when he's old and gray. Still, for a perverted, greedy, and lazy delinquent, there is more to Onizuka than meets the eye. So when he lands a job as the homeroom teacher of the Class 3-4 at the prestigious Holy Forest Academy—despite suplexing the Vice Principal—all of his talents are put to the test, as this class is particularly infamous.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Made in Abyss",
                listOf("Adventure", "Drama", "Fantasy", "Mystery", "Sci-Fi"),
                R.drawable.born,
                "2 seasons",
                "Made in Abyss : The Abyss—a gaping chasm stretching down into the depths of the earth, filled with mysterious creatures and relics from a time long past. How did it come to be? What lies at the bottom? Countless brave individuals, known as Divers, have sought to solve these mysteries of the Abyss, fearlessly descending into its darkest realms. The best and bravest of the Divers, the White Whistles, are hailed as legends by those who remain on the surface.", null
            )
        )

        listAnime.add(
            AnimeCatalog(
                "Cyberpunk: Edgerunners",
                listOf("Action", "Sci-Fi"),
                R.drawable.cyber,
                "1 season",
                "Cyberpunk: Edgerunners - Dreams are doomed to die in Night City, a futuristic Californian metropolis. As a teenager living in the city's slums, David Martinez is trying to fulfill his mother's lifelong wish for him to reach the top of Arasaka, the world's leading security corporation. To this end, he attends the prestigious Arasaka Academy while his mother works tirelessly to keep their family afloat.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Spy x Family",
                listOf("Action", "Award Winning", "Comedy"),
                R.drawable.spy,
                "2 seasons",
                "Spy x Family : Corrupt politicians, frenzied nationalists, and other warmongering forces constantly jeopardize the thin veneer of peace between neighboring countries Ostania and Westalis. In spite of their plots, renowned spy and master of disguise \"Twilight\" fulfills dangerous missions one after another in the hope that no child will have to experience the horrors of war.", null
            )
        )
        listAnime.add(
            AnimeCatalog(
                "Berserk",
                listOf("Action", "Adventure", "Drama", "Fantasy", "Horror"),
                R.drawable.berserk,
                "1 season",
                "Berserk : Guts, a man who will one day be known as the Black Swordsman, is a young traveling mercenary characterized by the large greatsword he carries. He accepts jobs that offer the most money, but he never stays with one group for long—until he encounters the Band of the Falcon. Ambushed after completing a job, Guts crushes many of its members in combat. Griffith, The Band of the Falcon's leader and founder, takes an interest in Guts and duels him. While the others are no match for Guts, Griffith defeats him in one blow.", null
            )
        )
    }



    override fun onStart() {
        super.onStart()
        pointerBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}