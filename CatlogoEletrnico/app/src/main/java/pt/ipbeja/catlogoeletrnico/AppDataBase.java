package pt.ipbeja.catlogoeletrnico;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class,Request.class,User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract BookDao getBookDao();
    public abstract RequestDao getRequestDao();
    public abstract UserDao getUserDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "myDB")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Factos Escondidos da História de Portugal'," +
                                    "'Hidden Facts from the History of Portugal'," +
                                    "'José Gomes Ferreira'," +
                                    "'Maio de 2021'," +
                                    "'Oficina do Livro'," +
                                    "'Os portugueses chegaram à América décadas antes de Colombo, descobriram a Austrália, participaram nos planos da viagem de Fernão de Magalhães… e outros dados históricos comprovados que permanecem na sombra.\n" +
                                    "\n" +
                                    "O Brasil foi descoberto mais de cinquenta anos antes da data oficial, como prova o mapa de Andrea Bianco de 1448;\n" +
                                    "O Canadá foi assim batizado por se tratar do nome da propriedade do descobridor algarvio João Vaz Corte Real: a Canada, em Tavira;\n" +
                                    "Os portugueses descobriram a Austrália. Mapas de 1447 mostram-na desenhada ao estilo da nossa cartografia e com nomes lusitanos.\n" +
                                    "\n" +
                                    "Estes e outros factos continuam arredados da História oficial dos países envolvidos, porque os interesses políticos, diplomáticos e económicos pesam mais do que a verdade.\n" +
                                    "\n" +
                                    "Com a ajuda das tecnologias de informação e o acesso a provas como mapas e outros documentos de época, cada vez mais cidadãos estão a tomar consciência de que a verdadeira História dos seus países tem muitas páginas escondidas - a de Portugal não é exceção, como nos revela esta investigação apaixonante do jornalista José Gomes Ferreira.'," +
                                    "'História de Portugal'," +
                                    "20," +
                                    "'https://img.wook.pt/images/factos-escondidos-da-historia-de-portugal-jose-gomes-ferreira/MXwyNDcxMDA5MnwyMDg2MzkzMXwxNjE4MjY4NDAwMDAwfHdlYnA=/502x')");
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Harry Potter e a Pedra Filosofal'," +
                                    "'Harry Potter and the Philosophers Stone'," +
                                    "'J. K. Rowling'," +
                                    "'Junho de 2020'," +
                                    "'Editorial Presença'," +
                                    "'«Voltando o sobrescrito ao contrário, com as mãos a tremer, Harry viu um selo de lacre cor de púrpura com um brasão onde podia distinguir-se um leão, uma águia, um texugo e uma serpente envolvendo a letra H.»\n\nHarry Potter nunca tinha ouvido falar de Hogwarts quando as cartas começaram a cair na entrada do número quatro, em Privet Drive.\n\nCom a morada escrita a verde num sobrescrito em papel pergaminho amarelo com lacre a roxo, são rapidamente confiscadas pelos seus tios horríveis.\n\nMas no dia do décimo primeiro aniversário de Harry, um gigante de olhos pretos e brilhantes chamado Rubeus Hagrid entra abruptamente com notícias surpreendentes: Harry Potter é um feiticeiro e tem um lugar à sua espera na escola de magia e feitiçaria de Hogwarts. Vai começar uma incrível aventura!'," +
                                    "'Juvenil'," +
                                    "40," +
                                    "'https://img.wook.pt/images/harry-potter-e-a-pedra-filosofal-j-k-rowling/MXwyNDA2Njc5OHwyMDEyNDQzMXwxNTkwNTM0MDAwMDAwfHdlYnA=/502x')");
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Águas Passadas'," +
                                    "'Past waters'," +
                                    "'João Tordo'," +
                                    "'Junho de 2021'," +
                                    "'Companhia das Letras'," +
                                    "'Durante treze dias de Janeiro de 2019, a chuva cai sem misericórdia sobre Lisboa. É quando aparece a primeira vítima, na praia de Assentiz: uma jovem de quinze anos trazida pela maré. O seu corpo apresenta marcas de sofisticada malvadez. A primeira agente no local é Pilar Benamor, uma subcomissária da PSP cuja coragem e empenho em descobrir a verdade ocultam segredos dolorosos.\n" +
                                    "\n" +
                                    "A jovem vítima é Charlie, filha de um empresário inglês, mas logo a vítima de um segundo crime brutal um rapaz de dezassete anos aparece na floresta de Monsanto, em condições inenarráveis. Estas duas mortes prematuras e violentas abrem caminho a uma investigação que irá descarnar a alta sociedade portuguesa e o submundo do crime.\n" +
                                    "\n" +
                                    "Ao longo desse inclemente mês de Inverno, Pilar desbrava caminho na investigação, contra tudo e todos e com a ajuda de Cícero, um misterioso eremita.\n" +
                                    "\n" +
                                    "Desobedecendo a ordens superiores e colocando a própria vida em risco, vai penetrar no mundo escuro e tenebroso de um psicopata, enquanto luta com os fantasmas que há muito carrega: um pai polícia que morreu em serviço, um vício que a consome e a vulnerabilidade num mundo dominado por homens.\n" +
                                    "\n" +
                                    "Depois da estreia no género com A Noite em Que o Verão Acabou, João Tordo regressa com um policial de ritmo imparável e delicada sensibilidade, que vai ao âmago dos nossos piores medos..'," +
                                    "'Policial e Thriller'," +
                                    "37," +
                                    "'https://img.wook.pt/images/aguas-passadas-joao-tordo/MXwyNDc4NTg5OXwyMDk2ODk4N3wxNjIxODEwODAwMDAwfHdlYnA=/502x')");
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Ágora'," +
                                    "'Ágora'," +
                                    "'Ana Luísa Amaral'," +
                                    "'Fevereiro de 2020'," +
                                    "'Assírio & Alvim'," +
                                    "'No mais recente livro de Ana Luísa Amaral, que conta com reproduções a cores de grandes obras de arte de todos os tempos, a poeta apresenta-nos um conjunto de poemas belos e terríveis, comoventes e violentos, em permanente diálogo com a Bíblia e com a arte, mas sobretudo com o nosso tempo.'," +
                                    "'Poesia'," +
                                    "100," +
                                    "'https://img.wook.pt/images/agora-ana-luisa-amaral/MXwyMzQxMzA1MnwxOTg0ODQxM3wxNTgyMDcwNDAwMDAwfHdlYnA=/502x')");
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Whats in a Name'," +
                                    "'O que está num Nome'," +
                                    "'Ana Luísa Amaral'," +
                                    "'Abril de 2020'," +
                                    "'Assírio & Alvim'," +
                                    "'«What’s in a Name» - A estranheza do inglês no título expressa a ambivalência da relação, de que sempre a poesia viveu, entre a coisa (nossa, do mundo) e a sua nomeação, apontando para a multiplicidade de sentidos que há neste livro. Nele se cruzam, em cumplicidade, o quotidiano e o cósmico, o poético e o político, a comoção e a ironia, o espanto e a indignação - em suma, a palavra e a vida.'," +
                                    "'Poesia'," +
                                    "75," +
                                    "'https://img.wook.pt/images/whats-in-a-name-ana-luisa-amaral/MXwxOTExODM2OXwxOTk2MTcxMnwxNTg1MDA4MDAwMDAwfHdlYnA=/502x')");
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('O Príncipe Cruel - Volume 1'," +
                                    "'The Cruel Prince - Volume 1'," +
                                    "'Holly Black'," +
                                    "'Março de 2020'," +
                                    "'TopSeller'," +
                                    "'Passaram dez anos desde que Jude e as irmãs foram raptadas pelo assassino dos seus pais e levadas para Faerie — o reino das fadas. Jude sente um verdadeiro fascínio pela beleza destes seres mágicos e imortais, mesmo sabendo que também são malévolos e impiedosos, e continua a sonhar em pertencer a este mundo encantado.\n" +
                                    "\n" +
                                    "Mas o povo das fadas despreza mortais e, para se tornar cavaleira e receber um lugar na Corte, Jude tem de arriscar a sua mortalidade e desafiar o príncipe Cardan, o filho mais novo e mais cruel do Rei Altíssimo. O príncipe odeia Jude e tudo fará para se ver livre dela. Tudo!\n" +
                                    "\n" +
                                    "É então que Jude se envolve nas intrigas e atividades de espionagem do palácio, acabando por descobrir o seu próprio talento para derramar sangue. E quando o seu sonho está prestes a tornar-se realidade, o destino de Faerie fica por um fio, obrigando Jude a fazer uma inesperada e perigosa aliança para salvar as irmãs e o reino que tanto a rejeita.\n" +
                                    "\n" +
                                    "As fadas não são de confiança,\n" +
                                    "Mesmo quando dizem a verdade…'," +
                                    "'Literatura Fantástica'," +
                                    "147," +
                                    "'https://img.wook.pt/images/o-principe-cruel-holly-black/MXwyMzg5NDg0MXwxOTkxNzg3M3wxNjIxMjA2MDAwMDAwfHdlYnA=/502x')");
                            db.execSQL("INSERT INTO User (name,date,email,phone,username,password,image) VALUES (" +
                                    "'Daniel Rodrigues'," +
                                    "'06/07/2002'," +
                                    "'21707@stu.ipbeja.pt'," +
                                    "'927487980'," +
                                    "'Gnomo'," +
                                    "'pass'," +
                                    "'https://s4.anilist.co/file/anilistcdn/user/avatar/large/b5271510-MiFJxt0NwyNc.png')");
                            db.execSQL("INSERT INTO User (name,date,email,phone,username,password,image) VALUES (" +
                                    "'Diogo Vaz'," +
                                    "'19/03/2002'," +
                                    "'21132@stu.ipbeja.pt'," +
                                    "'960497033'," +
                                    "'Vazinho'," +
                                    "'1234'," +
                                    "'https://s4.anilist.co/file/anilistcdn/character/large/b89361-x71P6YLrndd8.png')");
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
