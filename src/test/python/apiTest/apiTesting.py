import os
import unittest
from calibre.library import db

class TestCalibreLibrary(unittest.TestCase):

    def setUp(self):
        script_dir = os.path.dirname(__file__)
        library_path = os.path.abspath(os.path.join(script_dir, "..", "..", "..", "..", "library"))
        self.calibre_db = db(library_path).new_api

    def test_connection(self):
        """Test if the connection to the Calibre database is successful."""
        self.assertIsNotNone(self.calibre_db, "Failed to connect to Calibre database")

    def test_all_book_ids(self):
        """Test fetching all book IDs."""
        all_ids = self.calibre_db.all_book_ids()
        print("all ids = ", all_ids)
        self.assertEqual(all_ids, {2, 5, 6, 7, 8, 9, 10}, "all_book_ids did not return a list")

    def test_has_id(self):
        book_id = 9
        has_id = self.calibre_db.has_id(book_id)
        self.assertTrue(has_id, "Book ID should exist but was not found.")

    def test_get_metadata(self):
        """Test fetching metadata for a specific book."""
        book_id = 10
        metadata = self.calibre_db.get_metadata(book_id)

        # Expected metadata
        book_metadata = {
            "title": "New Title",
            "title_sort": "New Title",
            "authors": ["Ahed kh"],
            "tags": ["111111"],
            "series": {"name": "3", "index": 5},
            "timestamp": "2024-12-23T09:59:30+00:00",
            "published": "0101-01-01T00:00:00+00:00"
        }

        # Assert each attribute
        self.assertEqual(metadata.title, book_metadata["title"], "Title does not match")
        self.assertEqual(metadata.title_sort, book_metadata["title_sort"], "Title sort does not match")
        self.assertEqual(metadata.authors, book_metadata["authors"], "Authors do not match")
        self.assertEqual(metadata.tags, book_metadata["tags"], "Tags do not match")
        self.assertEqual(metadata.timestamp.isoformat(), book_metadata["timestamp"], "Timestamp does not match")


if __name__ == "__main__":
    loader = unittest.TestLoader()
    suite = loader.loadTestsFromTestCase(TestCalibreLibrary)
    runner = unittest.TextTestRunner()
    result = runner.run(suite)

    if not result.wasSuccessful():
        sys.exit(1)