package model;

import view.DisplayHT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;

public class AnalyseFile
{
    /**
     * Returns the hashtable of the given text, containing the occurence number
     * of the character combinations of given n.
     * @param text Given text
     * @param ht HashTable. If null, new one will be returned after being processed.
     *           If not, the process will be added to the given one.
     * @param n The n of N-gramme
     * @return The result
     */
    public static Hashtable<String, Integer> getHTofText(String text, Hashtable<String, Integer> ht, int n)
    {
        // If no hashtable given, make one
        if (ht == null)
        {
            ht = new Hashtable<>();
        }

        // If n is bigger than the length of the text, then impossible to get the hashtable
        int length = text.length();
        if (length - 1 < n)
        {
            // Might change this to an exception later
            System.out.println("n plus grand que la longueur de la texte. Renvoie de null...");
            return null;
        }

        // Getting the hashtable.
        // Maybe better if used threads for a bigger text?
        for (int i = 0; i < length - n; i++)
        {
            // Getting the char combination
            String comb = text.substring(i, i + n);

            // If it exists, we increment its value by 1
            if (ht.containsKey(comb))
            {
                int val = ht.get(comb);
                ht.put(comb, val + 1);
            }
            else
            {
                // If not, just add it to the hashtable
                ht.put(comb, 1);
            }
        }

        return ht;
    }


    /**
     * Calls getHTofText(text, null, n)
     * @param text Given text
     * @param n The n of N-gramme
     * @return The result
     */
    public static Hashtable<String, Integer> getHTofText(String text, int n)
    {
        return getHTofText(text, null, n);
    }


    /**
     * Sorts a hashtable, puts result in an ArrayList.
     * @param ht Hashtable to sort
     * @return Sorted in ArrayList
     */
    public static ArrayList<Couple<String, Integer>> sortHT(Hashtable<String, Integer> ht)
    {
        // If ht is null, return null
        if (ht == null)
        {
            return null;
        }

        ArrayList<Couple<String, Integer>> couples = new ArrayList<>();

        // Add the elements to the list
        for (String key : ht.keySet())
        {
            couples.add(new Couple(key, ht.get(key)));
        }

        // Sort the list using the func in Couple
        couples.sort(Couple::compareTo);

        // Reverse the list to get the biggest values at the top
        couples.sort(Comparator.reverseOrder());

        return couples;
    }




    public static void test()
    {
        String text = "Selon toutes les lois connues de l'aviation, il n'existe aucun moyen une abeille devrait être capable de voler. Ses ailes sont trop petites pour que son gros corps disparaisse. L'abeille bien sûr, vole de toute façon parce que les abeilles ne se soucient pas ce que les humains pensent est impossible. Jaune, noir. Jaune, noir. Jaune, noir. Ooh, noir et jaune! Secouons-le un peu. Barry! Le petit déjeuner est prêt! Ooming! Attends une seconde. Bonjour? Barry? -Adam? Pouvez-vous croire que cela se passe? - Je ne peux pas. Je te récupérerai. Regarder précisément. Utilisez les escaliers. Votre père a payé de l'argent pour ceux-là. Pardon. Je suis surexcité. Voici le diplômé. Nous sommes très fiers de toi, mon fils. Une carte de rapport parfaite, tous les B's. Très fier. Ma! J'ai une chose qui se passe ici. - Tu as de la peluche sur ton peluche. Ow! C'est moi! - Viens nous voir! Nous serons dans la rangée 118,00. -Au revoir! Barry, je t'ai dit d'arrêter de voler dans la maison! -Hey Adam. -Hey Barry. - C'est du gel? -Un peu. Journée spéciale, graduation. Jamais pensé que je le ferais. 3 jours d'école primaire, 3 jours d'école secondaire. C'était maladroit. 3 jours collège. Je suis content d'avoir pris une journée et fait de l'auto-stop dans la ruche. Vous êtes revenu autrement. -Hi Barry. -Artie, en train de pousser une moustache? Cela semble bon. - Pourquoi Frankie? -Ouais. - Tu vas aux funérailles? - Non, je ne vais pas. Tout le monde sait, piquer quelqu'un, vous mourez. Ne le perdez pas sur un écureuil. Une telle tête brûlante. Je suppose qu'il aurait pu juste sortir de la route. J'adore cette incorporation d'un parc d'attractions dans notre journée. C'est pourquoi nous n'avons pas besoin de vacances. Garçon, assez pompeux ... dans les circonstances. -Bien Adam, aujourd'hui nous sommes des hommes. -Nous sommes! -Bee-hommes. -Amen! Alléluia! Étudiants, professeurs, abeilles distinguées, s'il vous plaît la bienvenue Dean Buzzwell. Bienvenue, New Hive City diplômé classe de ... ... 9: 15. Cela conclut nos cérémonies. Et commence votre carrière chez Honex Industries! Choisirons-nous notre travail aujourd'hui? J'ai entendu dire que c'était juste une orientation. La tête haute! Et c'est parti. Gardez vos mains et antennes à l'intérieur du tramway et en tout temps. - Qu'il sera comme ça? -Un peu effrayant. Bienvenue chez Honex, une division de Honesco et une partie du groupe Hexagon. C'est ça! Sensationnel. Sensationnel. Nous savons que vous, comme une abeille, avez travaillé toute votre vie pour arriver au point où vous pouvez travailler pour toute votre vie. Le miel commence quand nos braves jumeaux de Pollen apportent le nectar à la ruche. Notre formule top secret est automatiquement corrigée de la couleur, parfum ajustée et bulle-contourné dans ce sirop doux apaisant avec sa lueur dorée distinctive que vous connaissez comme ... miel! - Cette fille était chaude. -C'est ma cousine! -Elle est? - Oui, nous sommes tous cousins. -Droite. Tu as raison. -A Honex nous nous efforçons constamment d'améliorer tous les aspects de l'existence de l'abeille. Ces abeilles testent une nouvelle technologie de casque. - Que pensez-vous qu'il fait? - Pas assez - Voici notre dernier avancement, le Krelman. - Qu'est-ce que ça fait? -Patches ce petit brin de miel qui pend après vous le verser. Nous sauve des millions. Quelqu'un peut-il travailler sur le Krelman? Bien sûr. La plupart des emplois d'abeilles sont de petite taille. Mais les abeilles savent que chaque petit travail, s'il est bien fait, signifie beaucoup. Mais choisissez attentivement parce que vous restez dans le travail que vous choisissez pour le reste de votre vie. Le même travail pour le reste de votre vie? Je ne le savais pas. Quelle est la différence? Vous serez heureux de savoir que les abeilles, en tant qu'espèce, n'ont pas eu un jour de congé en 27 millions d'années. Alors tu vas travailler avec nous ŕ mort? Nous allons essayer. Hou la la! Qui a soufflé mon esprit! \"Quelle est la différence?\" Comment peux-tu dire ça? Un emploi pour toujours? C'est un choix fou d'avoir à faire. Je suis soulagé. Maintenant, nous n'avons qu'à prendre une décision dans la vie. Mais, Adam, comment n'auraient-ils jamais pu nous le dire? Pourquoi ne poseriez-vous aucune question? Nous sommes des abeilles. Nous sommes la société la plus parfaitement en fonction sur la terre.";

        for (int i = 1; i < 4; i++)
        {
            System.out.println("================================================");
            Hashtable<String, Integer> ht = AnalyseFile.getHTofText(text, i);
            DisplayHT.print(ht, "Bee Movie Script",i);
        }
    }
}
