package com.example.myapplication.screens.thirdpage

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
import com.example.myapplication.databinding.FragmentThirdButtonBinding
import com.example.myapplication.screens.adapter.AdapterSerialsCatalog
import com.example.myapplication.screens.page.EveryItemView
import java.util.Locale


class ThirdButtonFragment : Fragment() {

    private lateinit var binding: FragmentThirdButtonBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var pointerBack: ImageView
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView
    private lateinit var adapter: AdapterSerialsCatalog
    private lateinit var cardView: ConstraintLayout

    private val listFilters = listOf("Drama", "Comedy", "Horror", "Sci-Fi", "Action", "Adventure")
    private var listSerial = ArrayList<SerialCatalog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdButtonBinding.inflate(inflater, container, false)
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
        adapter = AdapterSerialsCatalog(requireContext(), listSerial)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object  : AdapterSerialsCatalog.onItemClickListener {
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
            val filteredList = ArrayList<SerialCatalog>()
            for (i in listSerial) {
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
        val filteredList = listSerial.filter { movie ->
            movie.genre.contains(selectedGenre)
        }
        adapter.setFilteredList(filteredList)
    }

    fun addMovie(){
        listSerial.add(
            SerialCatalog(
                "Breaking Bad",
                listOf("Криминал", "Драма", "Триллер"),
                R.drawable.bad,
                "5 сезонов / 62 серии",
                "Химик Уолтер Уайт решает заняться производством наркотиков, чтобы обеспечить будущее своей семьи.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Game of Thrones",
                listOf("Фэнтези", "Драма", "Приключения"),
                R.drawable.thrones,
                "8 сезонов / 73 серии",
                "Игра престолов в вымышленном мире, где семьи борются за трон Железного Трона.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Stranger Things",
                listOf("Научная фантастика", "Драма", "Ужасы"),
                R.drawable.stranger,
                "4 сезона / 34 серии",
                "Мистические события в маленьком городе, связанные с исчезновением мальчика.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Crown",
                listOf("Драма", "Биография", "История"),
                R.drawable.crown,
                "5 сезонов / 50 серий",
                "История королевской семьи и ее взаимоотношений с политическими лидерами.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Friends",
                listOf("Комедия", "Романтика", "Ситком"),
                R.drawable.friends,
                "10 сезонов / 236 серий",
                "Жизнь шестерых друзей в Нью-Йорке, их приключения, радости и горести.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Walking Dead",
                listOf("Ужасы", "Драма", "Приключения"),
                R.drawable.walking,
                "11 сезонов / 177 серий",
                "Выживание группы людей в мире, захваченном зомби-апокалипсисом.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Sherlock",
                listOf("Детектив", "Драма", "Триллер"),
                R.drawable.sherlock,
                "4 сезона / 13 серий",
                "Современное переосмысление приключений Шерлока Холмса и доктора Ватсона.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Black Mirror",
                listOf("Научная фантастика", "Ужасы", "Триллер"),
                R.drawable.mirror,
                "5 сезонов / 22 серии",
                "Антология историй, раскрывающих темные стороны технологии и общества.",
                null
            )
        )


        listSerial.add(
            SerialCatalog(
                "Narcos",
                listOf("Криминал", "Драма", "Биография"),
                R.drawable.narc,
                "3 сезона / 30 серий",
                "История расцвета и падения наркокартелей в Колумбии.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Westworld",
                listOf("Научная фантастика", "Драма", "Триллер"),
                R.drawable.west,
                "4 сезона / 38 серий",
                "Тематический парк, где роботы обслуживают посетителей, но начинают проявлять свое сознание.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Office",
                listOf("Комедия", "Ситком", "Реалити-шоу"),
                R.drawable.office,
                "9 сезонов / 201 серий",
                "Забавные повседневные события в офисе бумажной компании.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "House of Cards",
                listOf("Драма", "Политика", "Триллер"),
                R.drawable.cardhouse,
                "6 сезонов / 73 серии",
                "Взлет и падение власти амбициозного политика в Вашингтоне.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Peaky Blinders",
                listOf("Драма", "Криминал", "История"),
                R.drawable.peanky,
                "6 сезонов / 30 серий",
                "Преступная семья Пики Блайндерс в Англии после Первой мировой войны.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Witcher",
                listOf("Фэнтези", "Драма", "Приключения"),
                R.drawable.witcher,
                "2 сезона / 16 серий",
                "Приключения геральта из Ривии, охотника на монстров, в мире волшебства и опасностей.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Mindhunter",
                listOf("Криминал", "Драма", "Триллер"),
                R.drawable.hunter,
                "2 сезона / 19 серий",
                "Расследование серийных убийц агентами ФБР во второй половине 20 века.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Chernobyl",
                listOf("Драма", "История", "Триллер"),
                R.drawable.chernobyl,
                "1 сезон / 5 серий",
                "События вокруг катастрофы на Чернобыльской АЭС и ее последствия.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Mandalorian",
                listOf("Научная фантастика", "Приключения", "Фэнтези"),
                R.drawable.manda,
                "3 сезона / 24 серии",
                "Приключения одиночки-мандалорца во вселенной Звездных Войн.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Money Heist",
                listOf("Криминал", "Драма", "Боевик"),
                R.drawable.moneyheist,
                "5 сезонов / 70 серий",
                "Группа грабителей планирует и совершает ограбление Королевской монетной казны Испании.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Haunting of Hill House",
                listOf("Ужасы", "Драма", "Мистика"),
                R.drawable.hillhouse,
                "1 сезон / 10 серий",
                "Семья, пережившая травматические события в мрачном доме, возвращается для разгадывания тайн прошлого.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Fargo",
                listOf("Криминал", "Драма", "Черный юмор"),
                R.drawable.fargo,
                "4 сезона / 41 серия",
                "Антология криминальных историй с черным юмором, разворачивающихся в небольших городках.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Big Bang Theory",
                listOf("Комедия", "Ситком", "Научная фантастика"),
                R.drawable.bigbang,
                "12 сезонов / 279 серий",
                "Приключения группы друзей-гиков и их отношения в мире науки и комиксов.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "House M.D.",
                listOf("Драма", "Медицина", "Триллер"),
                R.drawable.house,
                "8 сезонов / 177 серий",
                "Работа и гениальные методы диагностики знаменитого доктора Грегори Хауса.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Expanse",
                listOf("Научная фантастика", "Драма", "Приключения"),
                R.drawable.expanse,
                "6 сезонов / 71 серия",
                "Конфликт между Землей, Марсом и поясом астероидов в будущем, когда человечество распространилось по солнечной системе.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Vikings",
                listOf("Драма", "История", "Приключения"),
                R.drawable.viking,
                "6 сезонов / 89 серий",
                "История легендарных викингов, их подвиги и завоевания.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Wire",
                listOf("Криминал", "Драма", "Триллер"),
                R.drawable.thewire,
                "5 сезонов / 60 серий",
                "Исследование преступности и социальных проблем в Балтиморе через глаза полиции и преступников.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Dexter",
                listOf("Драма", "Триллер", "Криминал"),
                R.drawable.dexter,
                "8 сезонов / 96 серий",
                "Серийный убийца, работающий как судмедэксперт.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "The Marvelous Mrs. Maisel",
                listOf("Комедия", "Драма", "Ситком"),
                R.drawable.mrs,
                "4 сезона / 38 серий",
                "Женщина меняет свою жизнь, став комедийной стендап-комиком в 1950-х годах.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Fringe",
                listOf("Научная фантастика", "Детектив", "Триллер"),
                R.drawable.fring,
                "5 сезонов / 100 серий",
                "Расследование паранормальных явлений агентами ФБР.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Parks and Recreation",
                listOf("Комедия", "Ситком", "Политика"),
                R.drawable.parkrecreation,
                "7 сезонов / 125 серий",
                "Повседневная жизнь сотрудников департамента парков и рекреации.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Orphan Black",
                listOf("Научная фантастика", "Драма", "Триллер"),
                R.drawable.orphan,
                "5 сезонов / 50 серий",
                "Женщина узнает, что она клон, и вступает в опасное расследование своего происхождения.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "True Detective",
                listOf("Криминал", "Детектив", "Драма"),
                R.drawable.truedetect,
                "3 сезона / 24 серии",
                "Расследование серийных убийств, погружаясь в глубокие психологические темы.",
                null
            )
        )

        listSerial.add(
            SerialCatalog(
                "Homeland",
                listOf("Драма", "Триллер", "Шпионаж"),
                R.drawable.homeland,
                "8 сезонов / 96 серий",
                "Спецагент ЦРУ борется с террористическими угрозами и своими собственными демонами.",
                null
            )
        )
        listSerial.add(
            SerialCatalog(
                "The Handmaid's Tale",
                listOf("Драма", "Научная фантастика", "Триллер"),
                R.drawable.thehand,
                "5 сезонов / 63 серии",
                "Женщина борется за выживание в диктаторском обществе, где женщины лишены прав.",
                null
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